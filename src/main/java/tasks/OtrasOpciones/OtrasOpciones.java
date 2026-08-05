package tasks.OtrasOpciones;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class OtrasOpciones implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(OTRAS_OPCIONES));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar menú otras opciones");
    ReportHooks.registrarPaso("Seleccionar menú otras opciones");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR), WaitForResponse.withText(SELECCIONA));
    CapturaDePantallaMovil.tomarCapturaPantalla("Validacion mensaje boton selecciona");
    ReportHooks.registrarPaso("Validacion mensaje boton selecciona");

    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(SELECCIONA));
  }

  public static Performable otrasOpciones() {
    return instrumented(OtrasOpciones.class);
  }
}
