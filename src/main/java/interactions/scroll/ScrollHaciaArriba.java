package interactions.scroll;

import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.thucydides.core.annotations.Step;
import utils.AndroidObject;

import java.time.Duration;

public class ScrollHaciaArriba extends AndroidObject implements Interaction {

    @Override
    @Step("Realiza Scroll hacia arriba en el chat")
    public <T extends Actor> void performAs(T actor) {
        new TouchAction<>(androidDriver(actor))
                .press(PointOption.point(886, 1767))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(800)))
                .moveTo(PointOption.point(886, 400))
                .release()
                .perform();
    }

    public static Interaction scrollArriba() {
        return instrumented(ScrollHaciaArriba.class);
    }
}