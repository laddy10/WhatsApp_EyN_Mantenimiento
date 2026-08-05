package tasks.TusEquipos;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;
import interactions.wait.WaitForElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

public class ValidarFacturaConsultaConsumo implements Task {

    private static final String paso1 = "Validar menu principal disponible";
    private static final String paso2 = "Click en Menu principal";
    private static final String paso3 = "Click en Tu factura";
    private static final String paso4 = "Valida respuesta al dar click en descarga tu factura";
    private static final String paso5 = " Regrear al menú principal";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitFor.aTime(2000),
                WaitForTextContains.withTextContains(MENU_PRINCIPAL),
                ValidarTextoQueContengaX.elTextoContiene(ELIGE_UNA_OPCION)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                WaitForResponse.withText(TU_FACTURA),
                Click.on(BTN_TU_FACTURA),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForElement.waitFor(MENSAJE_AL_DIA_PAGOS, 45),
                ValidarTextoQueContengaX.elTextoContiene(DESCARGA_TU_FACTURA_PDF),
                ClickElementByText.clickElementByText(DESCARGA_TU_FACTURA),
                WaitFor.aTime(2000),
                WaitForElement.waitFor(PROTEGUE_INFO_SENSIBLE, 15)
        );
        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso5);

    }

    public static Performable validarFacturaConsultaConsumo() {
        return instrumented(ValidarFacturaConsultaConsumo.class);
    }
}