package tasks;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContainsWithTimeout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import questions.TextoQueContengaX;
import utils.CapturaDePantallaMovil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.FINALIZAR_CHAT;

/**
 * Saneo previo: cierra un CASO ABIERTO que quedo a medias de una ejecucion
 * anterior, ANTES de enviar el "Hola".
 */
public class CerrarCasoAbiertoSiExiste implements Task {

    private static final int TIMEOUT_CONFIRMACION_CIERRE =
            Integer.getInteger("whatsapp.cierrecaso.timeout.seconds", 15);

    @Override
    public <T extends Actor> void performAs(T actor) {

        // 1) ¿Hay un caso abierto a medias? La señal es el boton "Finalizar chat".
        boolean casoAbierto =
                TextoQueContengaX.verificarTexto(FINALIZAR_CHAT).answeredBy(actor);

        if (!casoAbierto) {
            ReportHooks.registrarPaso("No hay caso abierto previo; el chat esta limpio para iniciar");
            return;
        }

        CapturaDePantallaMovil.tomarCapturaPantalla("Caso abierto detectado de una ejecucion anterior");
        ReportHooks.registrarPaso("Caso abierto detectado. Se cierra con 'Finalizar chat' antes del saludo");

        // 2) Cerrar el caso con CLIC en "Finalizar chat" (no con texto)
        try {
            actor.attemptsTo(
                    ClickTextoQueContengaX.elTextoContiene(FINALIZAR_CHAT)
            );
        } catch (Exception e) {
            ReportHooks.registrarPaso(
                    "No se pudo hacer clic en 'Finalizar chat'; se continua e "
                            + "IniciarChatClaro intentara su propia recuperacion");
            return;
        }

        // 3) Confirmar que el bot cerro el caso (responde pidiendo "Hola" de nuevo)
        boolean cierreConfirmado = WaitForTextContainsWithTimeout.esperar(
                TIMEOUT_CONFIRMACION_CIERRE,
                "escribe la palabra Hola", "escribe la palabra hola",
                "Me encanta poder ayudarte",
                "Tus comentarios son muy importantes",
                "calificarme"
        ).answeredBy(actor);

        if (cierreConfirmado) {
            ReportHooks.registrarPaso("Caso anterior cerrado correctamente; chat listo para iniciar");
            CapturaDePantallaMovil.tomarCapturaPantalla("Caso anterior cerrado - chat limpio");
        } else {
            ReportHooks.registrarPaso(
                    "Se pulso 'Finalizar chat' pero no se detecto el mensaje de cierre; "
                            + "se continua y el flujo validara el estado");
        }

        // pequeña pausa para que la UI se estabilice antes del "Hola"
        actor.attemptsTo(WaitFor.aTime(1000));
    }

    public static Task ejecutar() {
        return instrumented(CerrarCasoAbiertoSiExiste.class);
    }
}
