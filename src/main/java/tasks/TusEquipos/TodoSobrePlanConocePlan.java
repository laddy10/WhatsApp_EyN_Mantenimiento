package tasks.TusEquipos;

import interactions.Click.ClickElementByText;
import interactions.comunes.Atras;
import interactions.wait.WaitFor;
import interactions.wait.WaitForElement;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_ENVIAR_MENU;
import static userinterfaces.WhatsAppPage.BTN_TUS_PQR_RADICADOS;
import static userinterfaces.WhatsAppPage.BTN_TUS_PQR_RADICADOS_DESCRIPCION;
import static userinterfaces.WhatsAppPage.BTN_VER_PQR;
import static utils.Constantes.*;

public class TodoSobrePlanConocePlan implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion Todo sobre tu plan";
    private static final String paso3 = "Seleccionar conoce tu plan";
    private static final String paso4 = "Descripcion del plan";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(TODO_SOBRE_TU_PLAN, 15),
                Click.on(BTN_TODO_SOBRE_TU_PLAN),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SELECCIONA, 15),
                Click.on(BTN_SELECCIONA),
                WaitForTextContains.withAnyTextContains(CONOCE_PLAN),
                Click.on(BTN_CONOCE_PLAN),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForTextContains.withAnyTextContains(DESCRIBIMOS_LO_QUE_TIENE_TU_PLAN),
                WaitForTextContains.withAnyTextContains(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso4);


    }

    public static Performable todoSobrePlanConocePlan() {
        return instrumented(TodoSobrePlanConocePlan.class);
    }
}
