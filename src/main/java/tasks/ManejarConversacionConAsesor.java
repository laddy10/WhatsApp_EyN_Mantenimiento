package tasks;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContainsWithTimeout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.CapturaDePantallaMovil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;

public class ManejarConversacionConAsesor implements Task {

    private static final int TIMEOUT_ASESOR = 900; // 15 minutos

    @Override
    public <T extends Actor> void performAs(T actor) {

        CapturaDePantallaMovil.tomarCapturaPantalla(
                "Conversación escalada a asesor humano"
        );

        ReportHooks.registrarPaso("Escalamiento a asesor detectado");

        // 1️⃣ Esperar saludo del asesor
        boolean asesorRespondio = WaitForTextContainsWithTimeout.esperar(
                TIMEOUT_ASESOR,
                "Mi nombre es",
                "Muy buen día",
                "Es un gusto atenderte",
                "Buen día",
                "Buenas tardes"
        ).answeredBy(actor);

        if (!asesorRespondio) {

            ReportHooks.registrarPaso(
                    "Timeout esperando respuesta del asesor"
            );

            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "Asesor no respondió dentro del tiempo esperado"
            );

            return;
        }

        ReportHooks.registrarPaso("Asesor respondió");

        // 2️⃣ Finalizar interacción
        actor.attemptsTo(
                Enter.theValue("Finalizar interacción").into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR)
        );

        ReportHooks.registrarPaso("Se envía mensaje para finalizar interacción");

        // 3️⃣ Esperar cierre de conversación por parte del asesor
        boolean cierreDetectado = WaitForTextContainsWithTimeout.esperar(
                TIMEOUT_ASESOR,
                "Nuestro chat con agente finalizó",
                "Fue un gusto ayudarte",
                "La conversación ha finalizado"
        ).answeredBy(actor);

        if (!cierreDetectado) {

            ReportHooks.registrarPaso(
                    "Timeout esperando cierre de conversación por asesor"
            );

            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "Asesor no cerró la conversación"
            );
        } else {

            ReportHooks.registrarPaso("Conversación finalizada por el asesor");

            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "Conversación con asesor finalizada correctamente"
            );
        }

        // 4️⃣ Limpiar chat para reiniciar flujo
        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BTN_MAS_OPCIONES),
                ClickTextoQueContengaX.elTextoContiene("Vaciar chat"),
                Click.on(BTN_VACIAR_CHAT)
        );

        ReportHooks.registrarPaso("Chat limpiado para reiniciar flujo");
    }

    public static ManejarConversacionConAsesor ejecutar() {
        return instrumented(ManejarConversacionConAsesor.class);
    }
}