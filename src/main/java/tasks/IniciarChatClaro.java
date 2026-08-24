package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForTextContains;
import interactions.wait.WaitForTextContainsWithTimeout;
import models.EstadoConversacion;
import net.serenitybdd.screenplay.Actor;
import utils.ClasificarRespuestaBot;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;
import utils.EstadoAtencionHumana;

public class IniciarChatClaro implements Task {

    private static final String paso1 = "Enviar saludo Hola al bot";
    private static final String paso2 = "Validar respuesta del bot Claro Empresas y Negocios";
    private static final int MAX_INTENTOS = 3;

    @Override
    public <T extends Actor> void performAs(T actor) {
        boolean flujoExitoso = false;

        // Si hay una marca persistente de atención humana, recuperar inmediatamente
        // el flujo sin enviar un nuevo saludo "Hola".
        if (EstadoAtencionHumana.requiereRecuperacion()) {
            ReportHooks.registrarPaso("Recuperando conversacion con asesor en estado: " + EstadoAtencionHumana.leerEstado());
            actor.attemptsTo(ManejarConversacionConAsesor.ejecutar());
        }

        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            
            // 1️⃣ Escribir Hola y enviar
            actor.attemptsTo(
                    Enter.theValue("Hola").into(TXT_ENVIAR_MENSAJE),
                    Click.on(BTN_ENVIAR)
            );
            EvidenciaUtils.registrarCaptura(paso1);

            // 2️⃣ Esperar respuesta del bot (Se mantienen los textos actuales)
            boolean respondioBot = WaitForTextContainsWithTimeout.esperar(
                    30,
                    "Claro Empresas y Negocios",
                    "Estoy aquí para ayudarte",
                    "¿Autorizas y aceptas?",
                    "Elige una de las opciones que tenemos para ti",
                    "No entendí tu mensaje",
                    "Aún estoy contigo"
            ).answeredBy(actor);

            if (!respondioBot) {
                EstadoAtencionHumana.marcarEnCola();
                ReportHooks.registrarPaso("Silencio del bot (30s); posible espera de asesor.");
                actor.attemptsTo(ManejarConversacionConAsesor.ejecutar());
                continue;
            }

            // 3️⃣ Obtener y evaluar el estado de la respuesta
            EstadoConversacion estado = ClasificarRespuestaBot.obtenerEstado(actor);

            switch (estado) {
                case PANTALLA_INICIAL:
                    actor.attemptsTo(SalirYReiniciarChat.ejecutar());
                    continue;

                case ERROR:
                    actor.attemptsTo(ValidarTextoErrorYLimpiarChat.validarYLimpiar());
                    continue;

                case ESPERANDO_ASESOR:
                    EstadoAtencionHumana.marcarEnCola();
                    actor.attemptsTo(ManejarConversacionConAsesor.ejecutar());
                    flujoExitoso = true;
                    break;

                case FLUJO_NORMAL:
                    flujoExitoso = true;
                    break; 
            }

            if (flujoExitoso) {
                break;
            }
        }

        if (!flujoExitoso) {
            throw new RuntimeException("No se pudo iniciar el flujo normal con Claro después de " + MAX_INTENTOS + " intentos.");
        }

        // 4️⃣ Validar textos de la respuesta (Se mantienen los textos actuales)
        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(CLARO_EMPRESAS_NEGOCIOS),
                ValidarTextoQueContengaX.elTextoContiene(ESTOY_AQUI_PARA_AYUDARTE)
        );
        EvidenciaUtils.registrarCaptura(paso2);
    }

    public static Performable iniciarChatClaro() {
        return instrumented(IniciarChatClaro.class);
    }
}