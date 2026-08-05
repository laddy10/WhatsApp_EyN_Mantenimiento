package tasks.HazTusRecargas;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class HazTusRecargas implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(HAZ_TUS_RECARGAS));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar el menu Haz tus recargas");
    ReportHooks.registrarPaso("Seleccionar el menu Haz tus recargas");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitForResponse.withText(SELECCIONA),
        ValidarTextoQueContengaX.elTextoContiene(HAZ_TUS_RECARGAS));

    CapturaDePantallaMovil.tomarCapturaPantalla(
        "Se valida que el mensaje contenga el botón Selecciona inicial");
    ReportHooks.registrarPaso("Se valida que el mensaje contenga el botón Selecciona inicial");
  }

  public static Performable hazTusRecargas() {
    return instrumented(HazTusRecargas.class);
  }
}
