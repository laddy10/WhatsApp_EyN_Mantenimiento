package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.EsperarYClickSeleccionaEnUltimoMensaje;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class TuLealtadMereceMas implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(TU_LEALTAD_MERECE_MAS));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar opcion Tu lealtad merece mas");
    ReportHooks.registrarPaso("Seleccionar opcion Tu lealtad merece mas");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitFor.aTime(9000),
        WaitForTextContains.withAnyTextContains(TEXTO_GRACIAS_POR_PREFERIRNOS));

    CapturaDePantallaMovil.tomarCapturaPantalla(
        "Validar botón Selecciona de tu lealtad merece más");
    ReportHooks.registrarPaso("Validar botón Selecciona de tu lealtad merece más");

    actor.attemptsTo(
        EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20),
        //   Click.on(BTN_SELECCIONA_TU_LEALTAD_2),
        ValidarTextoQueContengaX.elTextoContiene(CLARO_MUSICA),
        ValidarTextoQueContengaX.elTextoContiene(CLARO_DRIVE));
  }

  public static Performable tuLealtadMereceMas() {
    return instrumented(TuLealtadMereceMas.class);
  }
}
