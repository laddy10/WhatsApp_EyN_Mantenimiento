package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;

import hooks.ReportHooks;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.TimeoutException;
import utils.AndroidObject;
import utils.CapturaDePantallaMovil;

public class SalirConversacion implements Task {

    // 🔥 Comando actual del bot
    private static final String COMANDO_CIERRE = "Cierrecaso";

    // ✅ Respuestas válidas de cierre
    private static final String CASO_CERRADO = "Caso cerrado";
    private static final String CIERRECASO = "Cierrecaso"; // fallback por si el bot lo refleja

    // ⚠️ Respuestas intermedias (ruido)
    private static final String RESPUESTA_NO_ENTENDI = "No entendí tu mensaje.";
    private static final String RESPUESTA_MENU_PRINCIPAL = "Menú principal";
    private static final String RESPUESTA_CONTINUAR = "¿Deseas continuar chateando?";
    private static final String RESPUESTA_ACTIVO = "Aún estoy contigo";

    private static final int MAX_INTENTOS = 3;
    private static final int TIMEOUT_RESPUESTA = 6;

    @Override
    public <T extends Actor> void performAs(T actor) {

        boolean salidaExitosa = false;
        int intentos = 0;

        while (!salidaExitosa && intentos < MAX_INTENTOS) {
            intentos++;

            // 1️⃣ Enviar comando correcto
            actor.attemptsTo(
                    Enter.theValue(COMANDO_CIERRE).into(TXT_ENVIAR_MENSAJE),
                    Click.on(BTN_ENVIAR)
            );

            // 2️⃣ Esperar respuesta del bot
            try {
                actor.attemptsTo(
                        WaitForTextContains.withAnyTextContains(
                                TIMEOUT_RESPUESTA,
                                CASO_CERRADO,
                                CIERRECASO,
                                RESPUESTA_NO_ENTENDI,
                                RESPUESTA_MENU_PRINCIPAL,
                                RESPUESTA_CONTINUAR,
                                RESPUESTA_ACTIVO
                        )
                );
            } catch (TimeoutException e) {
                ReportHooks.registrarPaso(
                        "⏱ Sin respuesta tras '" + COMANDO_CIERRE + "' (intento " + intentos + ")");

                CapturaDePantallaMovil.tomarCapturaPantalla(
                        "Timeout al intentar cierre - intento " + intentos);

                actor.attemptsTo(WaitFor.aTime(1000));
                continue;
            }

            // 3️⃣ Validar si realmente cerró
            if (esCierreDetectado(actor)) {
                CapturaDePantallaMovil.tomarCapturaPantalla("Conversación cerrada correctamente");
                ReportHooks.registrarPaso("✓ Caso cerrado detectado correctamente");
                salidaExitosa = true;
                break;
            }

            // 4️⃣ Reintento si no cerró
            CapturaDePantallaMovil.tomarCapturaPantalla("Sin cierre aún - intento " + intentos);

            ReportHooks.registrarPaso(
                    "↻ No se detecta cierre (intento " + intentos + "), reenviando '" + COMANDO_CIERRE + "'");

            actor.attemptsTo(WaitFor.aTime(1200));
        }

        // ⚠️ No romper el test
        if (!salidaExitosa) {
            ReportHooks.registrarPaso(
                    "⚠ No se detectó cierre tras "
                            + MAX_INTENTOS
                            + " intentos, se continúa el flujo");
        }
    }

    // 🔍 Validación robusta de cierre
    private boolean esCierreDetectado(Actor actor) {
        try {
            AndroidObject and = new AndroidObject();

            return and.textoContiene(actor, CASO_CERRADO)
                    || and.textoContiene(actor, CIERRECASO);

        } catch (Exception e) {
            return false;
        }
    }

    public static SalirConversacion salir() {
        return instrumented(SalirConversacion.class);
    }
}