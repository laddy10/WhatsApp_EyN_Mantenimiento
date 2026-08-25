package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import userinterfaces.WhatsAppPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClicEnModoRedSeguro implements Interaction {

    public static ClicEnModoRedSeguro ejecutar() {
        return instrumented(ClicEnModoRedSeguro.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        // Buscamos si la segunda imagen con índice [2] está visible en pantalla
        boolean estaLaSegunda = WhatsAppPage.IMG_CONFIGURA_MODO_RED_2.resolveFor(actor).isPresent();

        if (estaLaSegunda) {
            // Si el scroll dejó ambas imágenes visibles, damos clic a la segunda [2]
            actor.attemptsTo(Click.on(WhatsAppPage.IMG_CONFIGURA_MODO_RED_2));
        } else {
            // Si el scroll dejó solo una imagen visible, damos clic sin usar índices
            actor.attemptsTo(Click.on(WhatsAppPage.IMG_AGRANDAR_FOTO));
        }
    }
}
