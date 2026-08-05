package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;
import static utils.ConstantesPost.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class TusEquipos implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(TUS_EQUIPOS));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar el menu Tus equipos");
    ReportHooks.registrarPaso("Seleccionar el menu Tus equipos");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitForResponse.withText(SELECCIONA),
        ValidarTextoQueContengaX.elTextoContiene(IMEI));
  }

  public static Performable tusEquipos() {
    return instrumented(TusEquipos.class);
  }
}
