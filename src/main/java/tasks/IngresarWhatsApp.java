package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.questions.Presence;
import utils.EvidenciaUtils;

public class IngresarWhatsApp implements Task {

  private static final String paso1 = "Ingreso a WhatsApp";

  @Override
  public <T extends Actor> void performAs(T actor) {

    if (!Presence.of(TXT_COPIA_SEGURIDAD).viewedBy(actor).resolveAll().isEmpty()) {
      actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(AHORA_NO));
    }

    if (!Presence.of(TXT_BIENVENIDA_WHATSAPP).viewedBy(actor).resolveAll().isEmpty()) {
      actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(ACEPTAR_Y_CONTINUAR_WH));
    }

    actor.attemptsTo(WaitForResponse.withText(PREGUNTAR_META));

    Presence.of(LOGO_WHATSAPP).viewedBy(actor).resolveAll();

    EvidenciaUtils.registrarCaptura(paso1);
  }

  public static IngresarWhatsApp ingresar() {
    return instrumented(IngresarWhatsApp.class);
  }
}