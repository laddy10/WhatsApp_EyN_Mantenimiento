package tasks.CompraTusPaquetes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.comunes.Atras;
import interactions.wait.EsperarYClickSeleccionaEnUltimoMensaje;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.CapturaDePantallaMovil;

public class PaqComunidadSorda implements Task {

  private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. Comunidad Sorda y enviar";
  private static final String MENSAJE_CAPTURA_2 =
      "Ingresar al boton Selecciona para validar los paquetes Comunidad Sorda";
  private static final String MENSAJE_CAPTURA_3 =
      "Se verifican los Paq. Comunidad Sorda disponibles";

  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(
        WaitForResponse.withText(PAQ_COMUNIDAD_SORDA),
        ClickTextoQueContengaX.elTextoContiene(PAQ_COMUNIDAD_SORDA));

    CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
    ReportHooks.registrarPaso(MENSAJE_CAPTURA);

    actor.attemptsTo(
        Click.on(BTN_ENVIAR_2), WaitForTextContains.withTextContains(LABEL_BTN_SELECCIONA));

    CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
    ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

    actor.attemptsTo(
        EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20),
        // Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
        WaitForResponse.withText(PAQ_CS_8000_7D_PRECIO));

    CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
    ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

    actor.attemptsTo(
        ValidarTexto.validarTexto(PAQ_CS_8000_7D_PRECIO),
        ValidarTexto.validarTexto(PAQ_CS_8000_7D_DESC),
        ValidarTexto.validarTexto(PAQ_CS_16500_15D_PRECIO),
        ValidarTexto.validarTexto(PAQ_CS_16500_15D_DESC),
        ValidarTexto.validarTexto(PAQ_CS_31500_30D_PRECIO),
        ValidarTexto.validarTexto(PAQ_CS_31500_30D_DESC));

    actor.attemptsTo(
        Atras.irAtras(),
        Enter.theValue(SALIR).into(TXT_ENVIAR_MENSAJE),
        Click.on(BTN_ENVIAR),
        WaitFor.aTime(2000),
        Enter.theValue(SALIR).into(TXT_ENVIAR_MENSAJE),
        Click.on(BTN_ENVIAR),
        WaitForResponse.withText(ABANDONAR_CONVERSACION));
  }

  public static Performable validarPaqComunidadSorda() {
    return instrumented(PaqComunidadSorda.class);
  }
}
