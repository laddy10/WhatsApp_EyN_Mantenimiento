package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import java.util.List;
import models.User;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;
import utils.TestDataProvider;

public class MenuPrincipal implements Task {

  private final User user = TestDataProvider.getRealUser();
  private static final String MENSAJE_CAPTURA =
      "Validación completa - Accediendo al menú principal";
  private static final String MENSAJE_CAPTURA_2 = "Seleccionando botón 'Ver menú prepago";
  private static final String MENSAJE_CAPTURA_3 = "Usuario con paquetes activos validado";
  private static final String MENSAJE_CAPTURA_4 = "Usuario sin paquetes activos - saldo validado";
  private static final String MENSAJE_CAPTURA_5 =
      "Detectado mensaje 'Conoce las opciones' - Ingresando a Menú principal";

  @Override
  public <T extends Actor> void performAs(T actor) {
    try {
      // Manejar botón "Ver menú prepago" si está presente
      manejarMenuPrepago(actor);

      // Validar estado del usuario (con/sin paquetes activos)
      validarEstadoUsuario(actor);

      // Evidenciar estado antes de acceder al menú principal
      CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
      ReportHooks.registrarPaso(MENSAJE_CAPTURA);

      // Navegar al menú principal
      actor.attemptsTo(
          ClickTextoQueContengaX.elTextoContiene(MENU_PRINCIPAL),
          WaitForResponse.withText(COMPRA_TUS_PAQUETES));

    } catch (Exception e) {
      CapturaDePantallaMovil.tomarCapturaPantalla("Error_Menu_Principal");
      ReportHooks.registrarPaso("ERROR: Fallo en navegación al menú principal - " + e.getMessage());
      throw e;
    }
  }

  private void manejarMenuPrepago(Actor actor) {
    List<WebElementFacade> btnVerMenuPrepago = BTN_VER_MENU_PREPAGO.resolveAllFor(actor);

    if (!btnVerMenuPrepago.isEmpty()) {
      CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
      ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

      actor.attemptsTo(
          ClickElementByText.clickElementByText(VER_MENU_PREPAGO),
          WaitForResponse.withText(MENU_PRINCIPAL));
    }
  }

  private void validarEstadoUsuario(Actor actor) {
    List<WebElementFacade> lblPaqueteActivo = LBL_PAQUETE_ACTIVO.resolveAllFor(actor);
    List<WebElementFacade> mensajeOpciones = LBL_CONOCE_OPCIONES.resolveAllFor(actor);

    if (!lblPaqueteActivo.isEmpty()) {
      // Usuario tiene paquetes activos
      actor.attemptsTo(
          ValidarTextoQueContengaX.elTextoContiene(PAQUETE_ACTIVO),
          ValidarTextoQueContengaX.elTextoContiene(VIGENCIA));
      CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
      ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

    } else if (!mensajeOpciones.isEmpty()) {

      actor.attemptsTo(WaitForResponse.withAnyText(MENU_PRINCIPAL));

      CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_5);
      ReportHooks.registrarPaso(MENSAJE_CAPTURA_5);

    } else {
      // Usuario sin paquetes activos - validar saldo
      actor.attemptsTo(
          ValidarTextoQueContengaX.elTextoContiene(user.getNumeroPre()),
          ValidarTextoQueContengaX.elTextoContiene(SALDO_NUMERO));
      CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_4);
      ReportHooks.registrarPaso(MENSAJE_CAPTURA_4);
    }
  }

  public static Performable menuPrincipal() {
    return instrumented(MenuPrincipal.class);
  }
}
