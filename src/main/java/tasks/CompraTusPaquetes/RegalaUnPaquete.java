package tasks.CompraTusPaquetes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_CONFIRMAR;
import static userinterfaces.WhatsAppPostpagoPage.LBL_PRIVACIDAD;
import static utils.Constantes.*;
import static utils.Constantes.COMPRA_DE_PAQUETES;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;

import java.util.List;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.ExtraerURL;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

public class RegalaUnPaquete implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar Regala un Paquete y enviar";
    private static final String MENSAJE_CAPTURA_2 = "Validar mensaje de regalo de paquete";
    private static final String MENSAJE_CAPTURA_3 = "Ingresar número de línea destino";
    private static final String MENSAJE_CAPTURA_4 = "Confirmar número y finalizar compra";
    private static final String MENSAJE_CAPTURA_5 =
            "Validar redirección al portal de pagos y recargas";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(REGALA_UN_PAQUETE),
                ClickTextoQueContengaX.elTextoContiene(REGALA_UN_PAQUETE));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitForResponse.withText(MENU_ANTERIOR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(ESCRIBIR_NUMERO_LINEA),
                Enter.theValue(NUMERO_REGALO).into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR),
                WaitForTextContains.withTextContains(ES_CORRECTO));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

        actor.attemptsTo(Click.on(BTN_CONFIRMAR), WaitFor.aTime(4000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_4);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_4);

        // Extraer URL dinámica del mensaje
        String mensaje =
                LBL_MENSAJES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .filter(text -> text.contains("http"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró mensaje con URL."));

        String urlExtraida = ExtraerURL.desdeTexto(mensaje);

        UtilidadesAndroid.abrirLinkEnNavegador(urlExtraida);

        actor.attemptsTo(
                WaitFor.aTime(10000), WaitForTextContains.withAnyTextContains(PORTAL_PAGOS_RECARGAS));

        List<WebElementFacade> lblprivacidad = LBL_PRIVACIDAD.resolveAllFor(actor);
        if (!lblprivacidad.isEmpty()) {
            actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(ACEPTAR));
        }

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(PORTAL_PAGOS_RECARGAS),
                ValidarTextoQueContengaX.elTextoContiene(COMPRA_DE_PAQUETES));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_5);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_5);

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );
    }

    public static Performable validarRegalaUnPaquete() {
        return instrumented(RegalaUnPaquete.class);
    }
}
