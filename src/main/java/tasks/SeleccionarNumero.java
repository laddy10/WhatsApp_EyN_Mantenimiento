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
import utils.ContextoST;

public class SeleccionarNumero implements Task {

  /** El valor tal como está en real-user.json: los últimos 4 dígitos o el número completo. */
  private final String numeroConfigurado;

  private final String ultimos4Digitos;

  public SeleccionarNumero(String numeroConfigurado) {
    this.numeroConfigurado = numeroConfigurado;
    // El bot de Claro enmascara los números en el menú (**** 9612), así que la
    // selección siempre se hace por los últimos 4 dígitos. Al normalizar aquí,
    // real-user.json puede guardar el número COMPLETO (que es lo que se quiere ver
    // en las alertas) sin romper la selección, y los valores viejos de 4 dígitos
    // siguen funcionando igual.
    this.ultimos4Digitos = ContextoST.ultimos4De(numeroConfigurado);
  }

  public static SeleccionarNumero porUltimos4(String numeroConfigurado) {
    return new SeleccionarNumero(numeroConfigurado);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {

    // Contrato st-context: qué línea PIDE el escenario. Se registra antes de tocar el
    // menú para que un fallo aquí (el bot no responde, la línea no está en el menú)
    // igual informe con qué línea se iba a probar.
    ContextoST.registrarLinea(numeroConfigurado, "menu");

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

    // La línea estaba en el menú del bot y es la opción que se va a enviar: dato
    // confirmado en pantalla, no una suposición.
    ContextoST.confirmarSeleccion(numeroConfigurado, opcionSeleccionada);

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
