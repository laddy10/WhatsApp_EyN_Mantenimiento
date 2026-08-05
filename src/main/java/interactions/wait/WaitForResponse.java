package interactions.wait;

import io.appium.java_client.MobileBy;
import java.util.Arrays;
import java.util.List;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitForResponse implements Interaction {

  private final List<String> expectedTexts;
  private final int timeout;

  // ⏱️ Tiempo por defecto en segundos
  private static final int DEFAULT_TIMEOUT = 30;

  public WaitForResponse(List<String> expectedTexts, int timeout) {
    this.expectedTexts = expectedTexts;
    this.timeout = timeout;
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    WebDriverWait wait = new WebDriverWait(BrowseTheWeb.as(actor).getDriver(), timeout);
    boolean found = false;
    long startTime = System.currentTimeMillis();

    while ((System.currentTimeMillis() - startTime) < timeout * 1000 && !found) {
      for (String text : expectedTexts) {
        try {
          By locator =
              new MobileBy.ByAndroidUIAutomator(
                  String.format("new UiSelector().textContains(\"%s\")", text));
          List<WebElement> elements = BrowseTheWeb.as(actor).getDriver().findElements(locator);
          if (!elements.isEmpty()) {
            found = true;
            break;
          }
        } catch (Exception ignored) {
        }
      }
    }

    if (!found) {
      throw new RuntimeException(
          "Ninguno de los textos esperados fue encontrado en el tiempo dado.");
    }
  }

  // ✅ Para un solo texto, con timeout personalizado
  public static WaitForResponse withText(String text, int timeoutSeconds) {
    return new WaitForResponse(Arrays.asList(text), timeoutSeconds);
  }

  // ✅ Para múltiples textos, con timeout personalizado
  public static WaitForResponse withAnyText(List<String> texts, int timeoutSeconds) {
    return new WaitForResponse(texts, timeoutSeconds);
  }

  public static WaitForResponse withAnyText(int timeoutSeconds, String... texts) {
    return new WaitForResponse(Arrays.asList(texts), timeoutSeconds);
  }

  // ✅ 🔁 Nuevos métodos con timeout por defecto

  public static WaitForResponse withText(String text) {
    return new WaitForResponse(Arrays.asList(text), DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withAnyText(String... texts) {
    return new WaitForResponse(Arrays.asList(texts), DEFAULT_TIMEOUT);
  }

  public static WaitForResponse withAnyText(List<String> texts) {
    return new WaitForResponse(texts, DEFAULT_TIMEOUT);
  }
}
