package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;

import java.util.List;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class ConoceMejoraTuPlan implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(CONOCE_MEJORA_TU_PLAN));

        CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar opcion 'Conoce/mejora tu plan'");
        ReportHooks.registrarPaso("Seleccionar opcion 'Conoce/mejora tu plan'");

        actor.attemptsTo(
                ClickElementByText.clickElementByText(ENVIAR),
                WaitForResponse.withAnyText(NOMBRE_DEL_PLAN, MEJORA_TU_PLAN));

        List<WebElementFacade> lblnombreplan = LBL_NOMBRE_PLAN.resolveAllFor(actor);
        if (!lblnombreplan.isEmpty()) {

            actor.attemptsTo(
                    ValidarTextoQueContengaX.elTextoContiene(NOMBRE_DEL_PLAN),
                    ValidarTextoQueContengaX.elTextoContiene(SEGUNDO),
                    ValidarTextoQueContengaX.elTextoContiene(NAVEGACION),
                    ValidarTextoQueContengaX.elTextoContiene(MENSAJES));
        } else {

            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "Se ingresa a 'Mejora tu plan' si no esta presente el nombre del plan");
            ReportHooks.registrarPaso(
                    "Se ingresa a 'Mejora tu plan' si no esta presente el nombre del plan");

            actor.attemptsTo(
                    ClickElementByText.clickElementByText(MEJORA_TU_PLAN),
                    WaitForResponse.withText(TU_PLAN_ACTUAL),
                    ValidarTextoQueContengaX.elTextoContiene(TU_PLAN_ACTUAL));
        }

        CapturaDePantallaMovil.tomarCapturaPantalla("Se valida el nombre del plan");
        ReportHooks.registrarPaso("Se valida el nombre del plan");

        actor.attemptsTo(SalirConversacion.salir());
    }

    public static Performable conoceMejoraTuPlan() {
        return instrumented(ConoceMejoraTuPlan.class);
    }
}
