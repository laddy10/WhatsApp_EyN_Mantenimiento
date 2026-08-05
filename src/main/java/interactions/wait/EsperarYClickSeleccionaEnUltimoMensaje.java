package interactions.wait;

import static userinterfaces.WhatsAppPage.LBL_MENSAJES;

import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class EsperarYClickSeleccionaEnUltimoMensaje implements Interaction {

  private final int timeoutSeg;
  private final int pollMs;

  public EsperarYClickSeleccionaEnUltimoMensaje(int timeoutSeg, int pollMs) {
    this.timeoutSeg = timeoutSeg;
    this.pollMs = pollMs;
  }

  public static EsperarYClickSeleccionaEnUltimoMensaje conTimeout(int timeoutSegundos) {
    return Tasks.instrumented(EsperarYClickSeleccionaEnUltimoMensaje.class, timeoutSegundos, 500);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    long fin = System.currentTimeMillis() + (timeoutSeg * 1000L);
    final String LABEL = "Selecciona";

    while (true) {
      if (System.currentTimeMillis() > fin) {
        throw new RuntimeException("No apareció el botón 'Selecciona' dentro del tiempo.");
      }

      // A) Buscar GLOBALMENTE cualquier chip "Selecciona"
      WebElement ultimoChip =
          ultimoChipSeleccionaVisible(BrowseTheWeb.as(actor).getDriver(), LABEL);
      if (ultimoChip != null) {
        try {
          ultimoChip.click();
          return;
        } catch (Exception ignored) {
          /* reintenta ciclo */
        }
      }

      // B) Respaldo: dentro/adyacente de la última burbuja (tu lógica)
      WebElementFacade ultimoMsg = ultimoMensaje(actor);
      if (ultimoMsg != null) {
        // dentro
        List<org.openqa.selenium.WebElement> dentro =
            ultimoMsg.findElements(
                By.xpath(
                    ".//*[@resource-id='com.whatsapp:id/button_content' or @text='"
                        + LABEL
                        + "' or @content-desc='"
                        + LABEL
                        + "']"));
        if (!dentro.isEmpty()) {
          dentro.get(dentro.size() - 1).click();
          return;
        }
        // hermano/adyacente
        List<org.openqa.selenium.WebElement> hermanos =
            ultimoMsg.findElements(
                By.xpath(
                    "../following-sibling::*//*[(@resource-id='com.whatsapp:id/button_content' or @text='"
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

  private static org.openqa.selenium.WebElement ultimoChipSeleccionaVisible(
      org.openqa.selenium.WebDriver driver, String label) {
    // candidatos: texto o content-desc "Selecciona", preferible clickable/focusable
    List<org.openqa.selenium.WebElement> cand =
        driver.findElements(
            By.xpath(
                "//*[(@text='"
                    + label
                    + "' or @content-desc='"
                    + label
                    + "') and (@clickable='true' or @focusable='true')]"));
    if (cand.isEmpty()) {
      cand =
          driver.findElements(
              By.xpath("//*[(@text='" + label + "' or @content-desc='" + label + "')]"));
    }

    // visibles y tomar el MÁS ABAJO en pantalla (mayor Y)
    java.util.List<org.openqa.selenium.WebElement> visibles =
        cand.stream()
            .filter(
                el -> {
                  try {
                    return el.isDisplayed() && el.isEnabled();
                  } catch (Exception e) {
                    return false;
                  }
                })
            .collect(java.util.stream.Collectors.toList());

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
