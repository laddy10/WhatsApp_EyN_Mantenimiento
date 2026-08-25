package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.WhatsAppPage;

public class TextoQueContengaX {

  public static Question<Boolean> verificarTexto(String texto) {
    return actor -> {
      try {
        return WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
            .anyMatch(element -> element.getText().contains(texto));
      } catch (org.openqa.selenium.StaleElementReferenceException e) {
        // Ignorar si la pantalla hace scroll o se actualiza mientras se leen los mensajes
        return false;
      }
    };
  }
}
