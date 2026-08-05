package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;
import static utils.ConstantesPost.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class TusEquiposEnSoporte implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        ClickTextoQueContengaX.elTextoContiene(SELECCIONA),
        ClickTextoQueContengaX.elTextoContiene(TUS_EQUIPOS_EN_SOPORTE));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar el menu Tus equipos en soporte");
    ReportHooks.registrarPaso("Seleccionar el menu Tus equipos en soporte");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitForTextContains.withAnyTextContains(NO_TIENES_EQUIPOS_MANTENIMIENTO),
        ValidarTextoQueContengaX.elTextoContiene(NO_TIENES_EQUIPOS_MANTENIMIENTO));
  }

  public static Performable tusEquiposEnSoporte() {
    return instrumented(TusEquiposEnSoporte.class);
  }
}
