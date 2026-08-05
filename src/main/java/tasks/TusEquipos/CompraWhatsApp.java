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

public class CompraWhatsApp implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Validar que exista la opción CompraWhatsApp";


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(COMPRA_WHATSAPP, 15)
        );

        EvidenciaUtils.registrarCaptura(paso2);

    }

    public static Performable compraWhatsApp() {
        return instrumented(CompraWhatsApp.class);
    }
}
