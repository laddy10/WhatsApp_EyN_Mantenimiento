package questions;

import net.serenitybdd.screenplay.Question;
import userinterfaces.WhatsAppPage;

public class TextoQueContengaX {

  public static Question<Boolean> verificarTexto(String texto) {
    return actor ->
        WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
            .anyMatch(element -> element.getText().contains(texto));
  }
}
