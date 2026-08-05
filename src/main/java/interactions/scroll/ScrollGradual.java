package interactions.scroll;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import utils.AndroidObject;

public class ScrollGradual extends AndroidObject implements Interaction {

    private final double porcentaje;
    private final boolean esBajar;

    public ScrollGradual(double porcentaje, boolean esBajar) {
        this.porcentaje = porcentaje;
        this.esBajar = esBajar;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            int width = androidDriver(actor).manage().window().getSize().getWidth();
            int height = androidDriver(actor).manage().window().getSize().getHeight();

            int startX = width / 2;
            int startY;
            int endY;

            if (esBajar) {
                // Swipe de abajo hacia arriba (desplaza la vista hacia abajo)
                startY = (int) (height * 0.65);
                endY = (int) (height * (0.65 - porcentaje));
            } else {
                // Swipe de arriba hacia abajo (desplaza la vista hacia arriba)
                startY = (int) (height * 0.35);
                endY = (int) (height * (0.35 + porcentaje));
            }

            new TouchAction(androidDriver(actor))
                    .press(PointOption.point(startX, startY))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                    .moveTo(PointOption.point(startX, endY))
                    .release()
                    .perform();
        } catch (Exception e) {
            System.out.println("Error al realizar el scroll gradual: " + e.getMessage());
        }
    }

    public static Interaction bajar(double porcentaje) {
        return instrumented(ScrollGradual.class, porcentaje, true);
    }

    public static Interaction bajarPorDefecto() {
        return instrumented(ScrollGradual.class, 0.18, true);
    }

    public static Interaction subir(double porcentaje) {
        return instrumented(ScrollGradual.class, porcentaje, false);
    }

    public static Interaction subirPorDefecto() {
        return instrumented(ScrollGradual.class, 0.18, false);
    }
}
