package interactions.wait;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;

import java.util.List;

public class WaitForElement implements Interaction {

    private final Target target;
    private final int timeout;
    private static final int DEFAULT_TIMEOUT = 30;

    public WaitForElement(Target target, int timeout) {
        this.target = target;
        this.timeout = timeout;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        long startTime = System.currentTimeMillis();
        boolean found = false;

        while ((System.currentTimeMillis() - startTime) < timeout * 1000L && !found) {
            try {
                List<WebElementFacade> elements = target.resolveAllFor(actor);
                if (!elements.isEmpty()) {
                    found = true;
                }
                if (!found) {
                    Thread.sleep(500);
                }
            } catch (Exception ignored) {
                // Continúa intentando
            }
        }

        if (!found) {
            throw new RuntimeException(
                    "No se encontró el elemento: " + target.getName()
                            + " en el tiempo dado (" + timeout + " segundos)");
        }
    }

    public static WaitForElement waitFor(Target target) {
        return new WaitForElement(target, DEFAULT_TIMEOUT);
    }

    public static WaitForElement waitFor(Target target, int timeoutSeconds) {
        return new WaitForElement(target, timeoutSeconds);
    }
}