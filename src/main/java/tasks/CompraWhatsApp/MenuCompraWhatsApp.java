package tasks.CompraWhatsApp;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.BTN_ENVIAR;
import static userinterfaces.WhatsAppPage.TXT_ENVIAR_MENSAJE;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class MenuCompraWhatsApp implements Task {

    private static final String MENSAJE_CAPTURA = "Validación correcta Compra por WhatsApp";

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            // Esperar y validar el elemento principal primero
            actor.attemptsTo(
                    WaitForTextContains.withAnyTextContains(INTERNET_PARA_TU_HOGAR),

                    // Realizar todas las validaciones
                    ValidarTextoQueContengaX.elTextoContiene(INTERNET_PARA_TU_HOGAR),
                    ValidarTextoQueContengaX.elTextoContiene(PLAN_PARA_TU_CELULAR),
                    ValidarTextoQueContengaX.elTextoContiene(CAMBIA_TU_CELULAR),
                    ValidarTextoQueContengaX.elTextoContiene(MENU_ANTERIOR));

            // Capturar evidencia de validación exitosa
            CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
            ReportHooks.registrarPaso(MENSAJE_CAPTURA);

            actor.attemptsTo(
                    Atras.irAtras(),
                    SalirConversacion.salir()
            );

        } catch (Exception e) {
            CapturaDePantallaMovil.tomarCapturaPantalla("Error_Validacion_Menu_WhatsApp");
            ReportHooks.registrarPaso("ERROR: Fallo en validación del menú - " + e.getMessage());
            throw e;
        }
    }

    public static Performable menuCompraWhatsApp() {
        return instrumented(MenuCompraWhatsApp.class);
    }
}
