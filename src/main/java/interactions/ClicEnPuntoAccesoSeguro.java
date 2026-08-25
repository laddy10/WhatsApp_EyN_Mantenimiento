package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WhatsAppPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClicEnPuntoAccesoSeguro implements Interaction {

    public static ClicEnPuntoAccesoSeguro ejecutar() {
        return instrumented(ClicEnPuntoAccesoSeguro.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        boolean estaElIndice1 = WhatsAppPage.IMG_CONFIGUR_PUNTO_ACCESO.resolveFor(actor).isPresent();

        if (estaElIndice1) {
            actor.attemptsTo(Click.on(WhatsAppPage.IMG_CONFIGUR_PUNTO_ACCESO));
        } else {
            actor.attemptsTo(Click.on(WhatsAppPage.IMG_AGRANDAR_FOTO));
        }
    }
}
