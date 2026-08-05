package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import java.util.List;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class ConsultarConsumos implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(CONSULTA_TUS_CONSUMOS));

    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar opcion 'Consulta tus consumos'");
    ReportHooks.registrarPaso("Seleccionar opcion 'Consulta tus consumos'");

    actor.attemptsTo(
        ClickElementByText.clickElementByText(ENVIAR),
        WaitForResponse.withAnyText(SIN_SALDO_DISPONIBLE, RECARGA_ACTIVA));

    List<WebElementFacade> lblsinsaldo = LBL_SIN_SALDO.resolveAllFor(actor);
    List<WebElementFacade> lblconsumopaquetes = LBL_CONSUMO_PAQUETES.resolveAllFor(actor);

    if (!lblsinsaldo.isEmpty()) {
      actor.attemptsTo(
          ValidarTextoQueContengaX.elTextoContiene(SIN_SALDO_DISPONIBLE),
          ValidarTexto.validarTexto(COMPRAR_PAQUETE),
          ValidarTexto.validarTexto(COMPRAR_RECARGA),
          ValidarTexto.validarTexto(MENU_ANTERIOR));
    } else {
      actor.attemptsTo(
          ValidarTextoQueContengaX.elTextoContiene(RECARGA_ACTIVA),
          ValidarTextoQueContengaX.elTextoContiene(SALDO_VENCE));
      if (!lblconsumopaquetes.isEmpty()) {
        actor.attemptsTo(
            ValidarTextoQueContengaX.elTextoContiene(CONSUMO),
            ValidarTextoQueContengaX.elTextoContiene(CONSUMOS),
            ValidarTextoQueContengaX.elTextoContiene(FECHA_ACTIVACION),
            ValidarTextoQueContengaX.elTextoContiene(PAQUETE_VENCE));
      }
    }

    CapturaDePantallaMovil.tomarCapturaPantalla("Se validan los consumos de la linea");
    ReportHooks.registrarPaso("Se validan los consumos de la linea");

    actor.attemptsTo(
            SalirConversacion.salir());
  }

  public static Performable ConsultarConsumos() {
    return instrumented(ConsultarConsumos.class);
  }
}
