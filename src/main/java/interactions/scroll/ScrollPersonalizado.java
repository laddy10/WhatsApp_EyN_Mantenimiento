package interactions.scroll;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;

import java.time.Duration;

public class ScrollPersonalizado extends AndroidObject implements Interaction {

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    public ScrollPersonalizado(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    @Override
    @Step("Realiza Scroll desde ({startX},{startY}) hasta ({endX},{endY})")
    public <T extends Actor> void performAs(T actor) {
        new TouchAction<>(androidDriver(actor))
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(endX, endY))
                .release()
                .perform();
    }

    // ✅ Sin instrumented, instancia directa
    public static Interaction scroll(int startX, int startY, int endX, int endY) {
        return new ScrollPersonalizado(startX, startY, endX, endY);
    }
}