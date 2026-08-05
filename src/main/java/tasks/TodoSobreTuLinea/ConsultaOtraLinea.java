package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.BTN_ENVIAR;
import static userinterfaces.WhatsAppPage.TXT_ENVIAR_MENSAJE;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.scroll.Scroll;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class ConsultaOtraLinea implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {
    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(CONSULTA_OTRA_LINEA));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar opcion 'Consulta otra línea'");
    ReportHooks.registrarPaso("Seleccionar opcion 'Consulta otra línea'");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitForTextContains.withAnyTextContains(
            LINEAS_PREPAGO, CUENTA_PROPIA, CONSULTA_FORMA_RAPIDA),
        ClickTextoQueContengaX.elTextoContiene(CUENTA_PROPIA),
        WaitForTextContains.withAnyTextContains(LINEAS_POSTPAGO, LINEAS_PREPAGO));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar Cuenta propia");
    ReportHooks.registrarPaso("Seleccionar Cuenta propia");

    actor.attemptsTo(
        Scroll.scrollUnaVista(),
        ValidarTextoQueContengaX.elTextoContiene(MAS_SERVICIOS_CLARO),
        ValidarTextoQueContengaX.elTextoContiene(LINEAS_POSTPAGO),
        ValidarTextoQueContengaX.elTextoContiene(LINEAS_PREPAGO));

    CapturaDePantallaMovil.tomarCapturaPantalla("Se valida redireccionamiento consulta otra linea");
    ReportHooks.registrarPaso("Se valida redireccionamiento consulta otra linea");

    actor.attemptsTo(
            SalirConversacion.salir());
  }

  public static Performable consultaOtraLinea() {
    return instrumented(ConsultaOtraLinea.class);
  }
}
