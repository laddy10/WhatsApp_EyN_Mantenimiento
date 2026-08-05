package interactions.scroll;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import utils.AndroidObject;

public class ScrollHastaTexto implements Interaction {

  private final String texto;

  public ScrollHastaTexto(String texto) {
    this.texto = texto;
  }

  public static ScrollHastaTexto conTexto(String texto) {
    return Tasks.instrumented(ScrollHastaTexto.class, texto);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    AndroidObject.scrollToText(actor, texto);
  }
}
