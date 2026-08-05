package tasks.TodoSobreTuLinea;

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

public class MenuTodoSobreTuLinea implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(TODO_SOBRE_TU_LINEA));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar opcion todo sobre tu linea");
    ReportHooks.registrarPaso("Seleccionar opcion todo sobre tu linea");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR), WaitForResponse.withText(SELECCIONA));

    CapturaDePantallaMovil.tomarCapturaPantalla("Validar mensaje que contenga el botón Selecciona");
    ReportHooks.registrarPaso("Validar mensaje que contenga el botón Selecciona");

    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(SELECCIONA));
  }

  public static Performable menuTodoSobreTuLinea() {
    return instrumented(MenuTodoSobreTuLinea.class);
  }
}
