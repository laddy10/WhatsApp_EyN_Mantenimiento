package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;

public class LineaMiNegocioOtros1 implements Task {

    private static final String paso1 = "Seleccionar Linea de mi negocio";
    private static final String paso2 = "Validar pregunta en cual servicio necesitas ayuda";
    private static final String paso3 = "Seleccionar Otros";
    private static final String paso4 = "Validar menu de opciones disponibles";
    private static final String paso5 = "Seleccionar opcion 1";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(LINEA_MI_NEGOCIO),
                WaitFor.aTime(1000)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(EN_CUAL_SERVICIO, 30),
                ValidarTextoQueContengaX.elTextoContiene(EN_CUAL_SERVICIO),
                ValidarTextoQueContengaX.elTextoContiene(OTROS)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(OTROS)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(NUMERO_NECESITES, 20)
        );
        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(NUMERO_NECESITES)
        );
        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                Enter.theValue("1").into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR)
        );
        EvidenciaUtils.registrarCaptura(paso5);
    }

    public static Performable lineaMiNegocioOtros1() {
        return instrumented(LineaMiNegocioOtros1.class);
    }
}