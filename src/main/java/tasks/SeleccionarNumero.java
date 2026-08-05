package tasks;

import static userinterfaces.WhatsAppPage.*;

import interactions.wait.WaitFor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;

public class SeleccionarNumero implements Task {

  private final String ultimos4Digitos;

  public SeleccionarNumero(String ultimos4Digitos) {
    this.ultimos4Digitos = ultimos4Digitos;
  }

  public static SeleccionarNumero porUltimos4(String ultimos4Digitos) {
    return new SeleccionarNumero(ultimos4Digitos);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(WaitFor.aTime(1000));

    // Obtener el texto del menú principal usando un XPath más flexible
    Target menuTexto = LBL_MENU_LINEAS;

    String textoMenu = Text.of(menuTexto).viewedBy(actor).asString();

    // Obtener la posición del número según los últimos 4 dígitos
    int opcionSeleccionada = obtenerPosicionPorUltimos4(textoMenu, ultimos4Digitos);

    if (opcionSeleccionada == -1) {
      throw new IllegalArgumentException(
          "No se encontró una línea con los últimos 4 dígitos: " + ultimos4Digitos);
    }

    // Ingresar la opción en el campo de texto
    actor.attemptsTo(
        WaitFor.aTime(1000),
        Enter.theValue(Integer.toString(opcionSeleccionada)).into(TXT_CAJA_MENSAJE),
        Click.on(BTN_ENVIAR));
  }

  /**
   * Obtiene la posición en el menú según los últimos 4 dígitos del número de línea. Retorna -1 si
   * no encuentra coincidencia.
   */
  private int obtenerPosicionPorUltimos4(String textoMenu, String ultimos4) {
    Pattern pattern = Pattern.compile("(\\d+) - \\*+(" + ultimos4 + ")");
    Matcher matcher = pattern.matcher(textoMenu);

    while (matcher.find()) {
      return Integer.parseInt(matcher.group(1)); // Retorna la opción encontrada
    }

    return -1; // No se encontró el número
  }
}
