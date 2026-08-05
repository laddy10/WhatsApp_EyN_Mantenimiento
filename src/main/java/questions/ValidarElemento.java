package questions;

import jxl.common.Logger;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

public class ValidarElemento implements Question<Boolean> {

  private static final Logger LOGGER = Logger.getLogger(ValidarElemento.class);
  private final Target element;

  public ValidarElemento(Target element) {
    this.element = element;
  }

  public static ValidarElemento esVisible(Target element) {
    return new ValidarElemento(element);
  }

  @Override
  public Boolean answeredBy(Actor actor) {
    LOGGER.info(" Elemento  visible : " + element.getName());
    boolean resultado = element.resolveFor(actor).isVisible();
    return resultado;
  }
}
