package tasks.CompraTusPaquetes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.comunes.Atras;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.CapturaDePantallaMovil;

public class PaqDeApps implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. de Apps y enviar";
    private static final String MENSAJE_CAPTURA_2 =
            "Ingresar al boton Selecciona para validar los paquetes de Apps";
    private static final String MENSAJE_CAPTURA_3 =
            "Se valida Paq. Youtube, Seleccionar Paquetes WhatsApp y enviar";
    private static final String MENSAJE_CAPTURA_4 =
            "Ingresar al boton Selecciona para validar paquetes";
    private static final String MENSAJE_CAPTURA_5 =
            "Se validan los paquetes de WhatsApp y se regresa al menu anterior";
    private static final String MENSAJE_CAPTURA_6 =
            "Ingresar al boton Selecciona y validar los paquetes de Apps";
    private static final String MENSAJE_CAPTURA_7 = "Seleccionar Paquetes Instagram y enviar";
    private static final String MENSAJE_CAPTURA_8 = "Ingresar al boton Selecciona y validar paquetes";
    private static final String MENSAJE_CAPTURA_9 =
            "Se validan los paquetes de Instagram y se regresa al menu anterior";
    private static final String MENSAJE_CAPTURA_10 =
            "Ingresar al boton Selecciona para validar paquetes de Apps";
    private static final String MENSAJE_CAPTURA_11 = "Seleccionar Paquetes Waze y enviar";

    private static final String MENSAJE_CAPTURA_12 = "Se validan los paquetes de Waze";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(PAQ_DE_APPS), ClickTextoQueContengaX.elTextoContiene(PAQ_DE_APPS));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitFor.aTime(13000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO), WaitForResponse.withText(PAQUETES_WHATSAPP));

        actor.attemptsTo(
                ValidarTexto.validarTexto(PAQ_YOUTUBE_3500_1H_PRECIO),
                ValidarTexto.validarTexto(PAQ_YOUTUBE_3500_1H_DESC),
                ClickTextoQueContengaX.elTextoContiene(PAQUETES_WHATSAPP));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitForResponse.withText(ELIGE_COMPRA));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_4);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_4);

        // Validar paquetes WhatsApp
        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                WaitForResponse.withText(PAQ_WHATSAPP_9500_15D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WHATSAPP_9500_15D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WHATSAPP_9500_15D_DESC),
                ValidarTexto.validarTexto(PAQ_WHATSAPP_18500_30D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WHATSAPP_18500_30D_DESC),
                ClickTextoQueContengaX.elTextoContiene(MENU_ANTERIOR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_5);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_5);

        // Regresar al menú de apps
        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitFor.aTime(3000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_6);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_6);

        // Validar paquetes Instagram
        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                ClickTextoQueContengaX.elTextoContiene(PAQUETES_INSTAGRAM));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_7);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_7);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitForResponse.withText(ELIGE_COMPRA));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_8);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_8);

        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                WaitForResponse.withText(PAQ_INSTAGRAM_3000_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_INSTAGRAM_3000_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_INSTAGRAM_3000_1D_DESC),
                ValidarTexto.validarTexto(PAQ_INSTAGRAM_15500_7D_PRECIO),
                ValidarTexto.validarTexto(PAQ_INSTAGRAM_15500_7D_DESC),
                ClickTextoQueContengaX.elTextoContiene(MENU_ANTERIOR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_9);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_9);

        // Regresar al menú de apps
        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitFor.aTime(3000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_10);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_10);

        // Validar paquetes Waze
        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                ClickTextoQueContengaX.elTextoContiene(PAQUETES_WAZE));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_11);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_11);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitForResponse.withText(ELIGE_COMPRA));

        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                WaitForResponse.withText(PAQ_WAZE_1500_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WAZE_1500_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WAZE_1500_1D_DESC),
                ValidarTexto.validarTexto(PAQ_WAZE_3500_7D_PRECIO),
                ValidarTexto.validarTexto(PAQ_WAZE_3500_7D_DESC));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_12);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_12);

        actor.attemptsTo(
                Atras.irAtras(),
                Enter.theValue(SALIR).into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR),
                WaitFor.aTime(2000),
                Enter.theValue(SALIR).into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR),
                WaitForResponse.withText(ABANDONAR_CONVERSACION));
    }

    public static Performable validarPaqDeApps() {
        return instrumented(PaqDeApps.class);
    }
}
