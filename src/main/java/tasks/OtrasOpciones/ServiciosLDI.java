package tasks.OtrasOpciones;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class ServiciosLDI implements Task {
  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(
        ClickTextoQueContengaX.elTextoContiene(TUS_SERVICIOS_LDI),
        ValidarTexto.validarTexto(TUS_SERVICIOS_LDI));

    CapturaDePantallaMovil.tomarCapturaPantalla("tus servicios LDI");
    ReportHooks.registrarPaso("tus servicios LDI");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR), WaitForResponse.withText(MENSAJE_LDI));
    CapturaDePantallaMovil.tomarCapturaPantalla("Validacion opciones");
    ReportHooks.registrarPaso("Validacion opciones");
  }

  public static Performable serviciosLDI() {
    return instrumented(ServiciosLDI.class);
  }
}
