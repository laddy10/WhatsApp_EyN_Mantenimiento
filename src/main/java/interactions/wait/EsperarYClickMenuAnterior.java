package interactions.wait;

import static userinterfaces.WhatsAppPage.LBL_MENSAJES;

import java.util.List;
import java.util.stream.Collectors;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;

public class EsperarYClickMenuAnterior implements Interaction {

  private final int timeoutSeg;
  private final int pollMs;

  public EsperarYClickMenuAnterior(int timeoutSeg, int pollMs) {
    this.timeoutSeg = timeoutSeg;
    this.pollMs = pollMs;
  }

  public static EsperarYClickMenuAnterior conTimeout(int timeoutSegundos) {
    return Tasks.instrumented(EsperarYClickMenuAnterior.class, timeoutSegundos, 500);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    long fin = System.currentTimeMillis() + (timeoutSeg * 1000L);
    final String LABEL = "Menú anterior";

    while (true) {
      if (System.currentTimeMillis() > fin) {
        throw new RuntimeException("No apareció el botón 'Menú anterior' dentro del tiempo.");
      }

      // A) Búsqueda global del chip "Menú anterior"
      org.openqa.selenium.WebElement ultimoChip =
          ultimoChipMenuAnteriorVisible(BrowseTheWeb.as(actor).getDriver(), LABEL);

      if (ultimoChip != null) {
        try {
          ultimoChip.click();
          return;
        } catch (Exception ignored) {
          // reintenta
        }
      }

      // B) Respaldo: dentro o adyacente al último mensaje
      WebElementFacade ultimoMsg = ultimoMensaje(actor);
      if (ultimoMsg != null) {

        List<org.openqa.selenium.WebElement> dentro =
            ultimoMsg.findElements(
                By.xpath(
                    ".//*[@resource-id='com.whatsapp:id/button_content' "
                        + "or @text='"
                        + LABEL
                        + "' or @content-desc='"
                        + LABEL
                        + "']"));
        if (!dentro.isEmpty()) {
          dentro.get(dentro.size() - 1).click();
          return;
        }

        List<org.openqa.selenium.WebElement> hermanos =
            ultimoMsg.findElements(
                By.xpath(
                    "../following-sibling::*//*[(@resource-id='com.whatsapp:id/button_content' "
                        + "or @text='"
                        + LABEL
                        + "' or @content-desc='"
                        + LABEL
                        + "')]"));
        if (!hermanos.isEmpty()) {
          hermanos.get(hermanos.size() - 1).click();
          return;
        }
      }

      dormir(pollMs);
    }
  }

  private static org.openqa.selenium.WebElement ultimoChipMenuAnteriorVisible(
      org.openqa.selenium.WebDriver driver, String label) {

    List<org.openqa.selenium.WebElement> candidatos =
        driver.findElements(
            By.xpath(
                "//*[(@text='"
                    + label
                    + "' or @content-desc='"
                    + label
                    + "') "
                    + "and (@clickable='true' or @focusable='true')]"));

    if (candidatos.isEmpty()) {
      candidatos =
          driver.findElements(
              By.xpath("//*[(@text='" + label + "' or @content-desc='" + label + "')]"));
    }

    List<org.openqa.selenium.WebElement> visibles =
        candidatos.stream()
            .filter(
                el -> {
                  try {
                    return el.isDisplayed() && el.isEnabled();
                  } catch (Exception e) {
                    return false;
                  }
                })
            .collect(Collectors.toList());

    return visibles.stream()
        .max(
            java.util.Comparator.comparingInt(
                el -> {
                  try {
                    return el.getRect().y;
                  } catch (Exception e) {
                    return Integer.MIN_VALUE;
                  }
                }))
        .orElse(null);
  }

  private static WebElementFacade ultimoMensaje(Actor actor) {
    List<WebElementFacade> mensajes = LBL_MENSAJES.resolveAllFor(actor);
    return mensajes.isEmpty() ? null : mensajes.get(mensajes.size() - 1);
  }

  private static void dormir(long ms) {
    try {
      Thread.sleep(ms);
    } catch (InterruptedException ignored) {
    }
  }
}
