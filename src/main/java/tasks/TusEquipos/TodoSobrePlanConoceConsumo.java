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

public class TodoSobrePlanConoceConsumo implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion Todo sobre tu plan";
    private static final String paso3 = "Seleccionar consulta tus consumos";
    private static final String paso4 = "Descripcion del consumo";

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
                WaitForTextContains.withAnyTextContains(CONOCE_CONSUMO),
                Click.on(BTN_CONSULTA_TU_CONSUMO),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForTextContains.withAnyTextContains(
                        CONSUMO_DATOS_A_LA_FECHA,
                        SIN_CONSUMO_DATOS)
        );

        EvidenciaUtils.registrarCaptura(paso4);


    }

    public static Performable todoSobrePlanConoceConsumo() {
        return instrumented(TodoSobrePlanConoceConsumo.class);
    }
}
