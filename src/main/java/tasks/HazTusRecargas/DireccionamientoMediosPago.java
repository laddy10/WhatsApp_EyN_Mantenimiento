package tasks.HazTusRecargas;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.scroll.ScrollInicio;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.ExtraerURL;
import tasks.SalirConversacion;
import userinterfaces.WhatsAppPage;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

public class DireccionamientoMediosPago implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (!BTN_CONTINUAR_RECARGA.resolveAllFor(actor).isEmpty()) {
            actor.attemptsTo(Click.on(BTN_CONTINUAR_RECARGA), WaitForResponse.withText(MEDIOS_DE_PAGO));
            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "Dar clic en boton 'Continuar recarga' y se habilita el boton 'Medios de pago");
            ReportHooks.registrarPaso(
                    "Dar clic en boton 'Continuar recarga' y se habilita el boton 'Medios de pago");
        }

        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(MEDIOS_DE_PAGO));

        realizarFlujoMedioPago(actor, NEQUI, SIN_CUENTA_NEQUI, SMS_NEQUI_NO_DISPONIBLE);
        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(MEDIOS_DE_PAGO));

        if (!LBL_TARJETA_DEBITO_CREDITO.resolveAllFor(actor).isEmpty()) {
            realizarFlujoConLink(actor, TARJETA_DEBIDO_O_CREDITO, URL_PAGO_RECARGAS);
            actor.attemptsTo(
                    Atras.irAtras(),
                    ScrollInicio.scrollUnaVista(),
                    ClickTextoQueContengaX.elTextoContiene(MEDIOS_DE_PAGO));
            realizarFlujoConLink(actor, PSE, URL_PAGO_PSE_RECARGAS);
        }

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );

        //    WordAppium.main();
    }

    private <T extends Actor> void realizarFlujoMedioPago(
            T actor, String medioPago, String... textosValidacion) {
        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(medioPago), WaitForResponse.withText(ENVIAR2));

        CapturaDePantallaMovil.tomarCapturaPantalla(
                "Seleccionar el medio de pago Nequi para continuar con la recarga.");
        ReportHooks.registrarPaso("Seleccionar el medio de pago Nequi para continuar con la recarga.");

        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(ENVIAR2),
                WaitForResponse.withAnyText(textosValidacion));

        CapturaDePantallaMovil.tomarCapturaPantalla(
                "Se valida el mensaje correspondiente al medio de pago Nequi.");
        ReportHooks.registrarPaso("Se valida el mensaje correspondiente al medio de pago Nequi.");

        if (!LBL_SIN_CUENTA_NEQUI.resolveAllFor(actor).isEmpty()) {
            actor.attemptsTo(ValidarTexto.validarTexto(SIN_CUENTA_NEQUI));
        } else {
            actor.attemptsTo(ValidarTexto.validarTexto(SMS_NEQUI_NO_DISPONIBLE));
        }
    }

    private <T extends Actor> void realizarFlujoConLink(
            T actor, String medioPago, String textoEnlace) {
        // Verificar si el medio de pago está presente en pantalla
        boolean medioPagoDisponible =
                WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
                        .anyMatch(el -> el.getText().contains(medioPago));

        if (!medioPagoDisponible) {
            CapturaDePantallaMovil.tomarCapturaPantalla("Medio de pago no disponible: " + medioPago);
            ReportHooks.registrarPaso("Medio de pago no disponible en esta recarga: " + medioPago);
            return; // Salta este flujo
        }

        // Continuar si está disponible
        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(medioPago), WaitForResponse.withText(ENVIAR2));

        CapturaDePantallaMovil.tomarCapturaPantalla(
                "Seleccionar el medio de pago: " + medioPago + " para continuar con la recarga.");
        ReportHooks.registrarPaso("Seleccionar el medio de pago: " + medioPago);

        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(ENVIAR2),
                WaitForResponse.withText(SMS_ENLACE_PAGO),
                WaitFor.aTime(3000),
                ValidarTextoQueContengaX.elTextoContiene(textoEnlace));

        CapturaDePantallaMovil.tomarCapturaPantalla("Validar mensaje del medio de pago: " + medioPago);
        ReportHooks.registrarPaso("Validar mensaje del medio de pago: " + medioPago);

        String mensaje =
                WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .filter(text -> text.contains(textoEnlace))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró mensaje con URL."));

        String urlExtraida = ExtraerURL.desdeTexto(mensaje);
        UtilidadesAndroid.abrirLinkEnNavegador("http://" + urlExtraida);

        actor.attemptsTo(
                WaitForResponse.withText(BIENVENIDO),
                ValidarTexto.validarTexto(ECARRIER),
                ValidarTexto.validarTexto(BIENVENIDO),
                ValidarTexto.validarTexto(NUMERO_CLARO));

        CapturaDePantallaMovil.tomarCapturaPantalla(
                "Se verifica el correcto redireccionamiento al enlace de pago: " + medioPago);
        ReportHooks.registrarPaso(
                "Se verifica el correcto redireccionamiento al enlace de pago: " + medioPago);
    }

    public static Performable direccionamientoMediosPago() {
        return instrumented(DireccionamientoMediosPago.class);
    }
}
