package tasks.TusEquipos;

import interactions.Click.ClickElementByText;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_ENVIAR_MENU;
import static utils.Constantes.*;

public class TodoSobrePlanCancelarPlan implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion Todo sobre tu plan";
    private static final String paso3 = "Validar que exista la opción modifica tu plan";
    private static final String paso4 = "Regresar y validar seleccioanr para terminar el flujo";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(TODO_SOBRE_TU_PLAN, 50),
                Click.on(BTN_TODO_SOBRE_TU_PLAN),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SELECCIONA, 50),
                Click.on(BTN_SELECCIONA),
                WaitForTextContains.withAnyTextContains(CANCELAR_PLAN),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                Click.on(BTN_CERRAR),
                WaitForTextContains.withTextContains(SELECCIONA)
        );

        EvidenciaUtils.registrarCaptura(paso4);

    }

    public static Performable todoSobrePlanCancelarPlan() {
        return instrumented(TodoSobrePlanCancelarPlan.class);
    }
}
