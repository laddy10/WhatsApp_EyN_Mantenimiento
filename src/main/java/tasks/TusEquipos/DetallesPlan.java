package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.EvidenciaUtils;

public class DetallesPlan implements Task {

    private static final String paso1 = "Validar opciones rapidas que te pueden ayudar";
    private static final String paso2 = "Validar opcion detalle de tu plan";
    private static final String paso3 = "Click en detalle de tu plan";
    private static final String paso4 = "Validar descripcion del plan";

    @Override
    public <T extends Actor> void performAs(T actor) {


        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(OPCIONES_RAPIDAS)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(DETALLE_DE_TU_PLAN),
                ValidarTextoQueContengaX.elTextoContiene(ELIGE_UNA_OPCION)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(DETALLE_DE_TU_PLAN)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForResponse.withText(DESCRIPCION_PLAN),
                WaitForResponse.withText(MENU_PRINCIPAL)

        );

        EvidenciaUtils.registrarCaptura(paso4);


    }

    public static Performable detallesPlan() {
        return instrumented(DetallesPlan.class);
    }
}