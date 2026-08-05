package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Click.ClickTextoQueContengaX;
import java.util.Arrays;
import java.util.List;
import models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import questions.TextoQueContengaX;
import utils.TestDataProvider;

public class ValidarTextoErrorYLimpiarChat implements Task {

  private final User user = TestDataProvider.getRealUser();

  @Override
  public <T extends Actor> void performAs(T actor) {
    List<String> mensajesError =
        Arrays.asList(
            "Por favor ingrese el número de opción",
            "¡Lo siento! No entendí lo que escribiste",
            "No entendí tu mensaje.",
            "Menú principal",
            "Para continuar debes seleccionar alguna de las opciones mostradas anteriormente",
            "Lo siento, tu respuesta no es válida");

    boolean textoErrorDetectado =
        mensajesError.stream()
            .anyMatch(msg -> TextoQueContengaX.verificarTexto(msg).answeredBy(actor));

    if (textoErrorDetectado) {
      actor.attemptsTo(
          SalirConversacion.salir(),
          Click.on(BTN_MAS_OPCIONES),
          ClickTextoQueContengaX.elTextoContiene(MAS),
          ClickTextoQueContengaX.elTextoContiene(VACIAR_CHAT),
          Click.on(BTN_VACIAR_CHAT),
          Enter.theValue(user.getSaludo()).into(TXT_ENVIAR_MENSAJE),
          Click.on(BTN_ENVIAR));
    }
  }

  public static ValidarTextoErrorYLimpiarChat validarYLimpiar() {
    return instrumented(ValidarTextoErrorYLimpiarChat.class);
  }
}
