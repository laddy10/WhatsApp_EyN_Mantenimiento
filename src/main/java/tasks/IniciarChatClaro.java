package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForTextContains;
import models.EstadoConversacion;
import net.serenitybdd.screenplay.Actor;
import utils.ClasificarRespuestaBot;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;

public class IniciarChatClaro implements Task {

    private static final String paso1 = "Enviar saludo Hola al bot";
    private static final String paso2 = "Validar respuesta del bot Claro Empresas y Negocios";
    private static final int MAX_INTENTOS = 3;

    @Override
    public <T extends Actor> void performAs(T actor) {
        boolean flujoExitoso = false;

        for (int intento = 1; intento <= MAX_INTENTOS; intento++) {
            
            // 1️⃣ Escribir Hola y enviar
            actor.attemptsTo(
                    Enter.theValue("Hola").into(TXT_ENVIAR_MENSAJE),
                    Click.on(BTN_ENVIAR)
            );
            EvidenciaUtils.registrarCaptura(paso1);

            // 2️⃣ Esperar respuesta del bot
            actor.attemptsTo(
                    WaitForTextContains.withAnyTextContains(
                            30,
                            "Claro Empresas y Negocios",
                            "Estoy aquí para ayudarte",
                            "¿Autorizas y aceptas?",
                            "Elige una de las opciones que tenemos para ti",
                            "No entendí tu mensaje",
                            "Aún estoy contigo"
                    )
            );

            // 3️⃣ Obtener y evaluar el estado de la respuesta
            EstadoConversacion estado = ClasificarRespuestaBot.obtenerEstado(actor);

            switch (estado) {
                case PANTALLA_INICIAL:
                    actor.attemptsTo(SalirYReiniciarChat.ejecutar());
                    continue;

                case ERROR:
                    actor.attemptsTo(ValidarTextoErrorYLimpiarChat.validarYLimpiar());
                    continue;

                case FLUJO_NORMAL:
                case ESPERANDO_ASESOR:
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

        // 4️⃣ Validar textos de la respuesta
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