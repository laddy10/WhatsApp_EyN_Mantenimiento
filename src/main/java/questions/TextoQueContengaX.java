package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.WhatsAppPage;

public class TextoQueContengaX {

  public static Question<Boolean> verificarTexto(String texto) {
    return actor -> {
      try {
        return WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
            .anyMatch(element -> {
                try {
                    String t = element.getText();
                    return t != null && t.contains(texto);
                } catch (org.openqa.selenium.StaleElementReferenceException e) {
                    return false;
                }
            });
      } catch (org.openqa.selenium.StaleElementReferenceException | NullPointerException e) {
        // Ignorar si la pantalla hace scroll o el driver devuelve null momentáneamente
        return false;
      }
    };
  }
}
