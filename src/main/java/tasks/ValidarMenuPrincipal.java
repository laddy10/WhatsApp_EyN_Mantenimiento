package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Validaciones.ValidarTexto;
import interactions.comunes.Atras;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.CapturaDePantallaMovil;

public class ValidarMenuPrincipal implements Task {

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(
        ValidarTexto.validarTexto(MENU_PRINCIPAL),
        ValidarTexto.validarTexto(COMPRA_TUS_PAQUETES),
        ValidarTexto.validarTexto(HAZ_TUS_RECARGAS),
        ValidarTexto.validarTexto(COMPRA_POR_WHATSAPP),
        ValidarTexto.validarTexto(TUS_EQUIPOS),
        ValidarTexto.validarTexto(TODO_SOBRE_TU_LINEA2),
        ValidarTexto.validarTexto(OTRAS_OPCIONES),
        ValidarTexto.validarTexto(HABLA_CON_ASESOR),
        ValidarTexto.validarTexto(CONSULTAR_OTRA_CUENTA));

    CapturaDePantallaMovil.tomarCapturaPantalla("Validar Menu principal y opciones");
    ReportHooks.registrarPaso("Validar Menu principal y opciones");

    actor.attemptsTo(Atras.irAtras(), SalirConversacion.salir());
  }

  public static Performable validarMenuPrincipal() {
    return instrumented(ValidarMenuPrincipal.class);
  }
}
