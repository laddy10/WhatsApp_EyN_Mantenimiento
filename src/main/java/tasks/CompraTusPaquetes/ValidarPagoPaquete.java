package tasks.CompraTusPaquetes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.scroll.ScrollHastaTexto;
import interactions.scroll.ScrollInicio;
import interactions.wait.*;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.questions.Presence;

import tasks.ExtraerURL;
import tasks.SalirConversacion;
import userinterfaces.WhatsAppPage;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

import static userinterfaces.WhatsAppPage.BTN_COMPRAR_PAQ_PREPAGO;


public class ValidarPagoPaquete implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. todo incluido y enviar";
    private static final String MENSAJE_CAPTURA_2 = "Seleccionar Ver más paquetes y enviar";
    private static final String MENSAJE_CAPTURA_3 = "Seleccionar paquete y enviar";
    private static final String MENSAJE_CAPTURA_4 = "Comprar paq. prepago y continuar compra";
    private static final String MENSAJE_CAPTURA_5 =
            "Validar información del paquete y acceder a medios de pago";
    private static final String MENSAJE_CAPTURA_6 = "Seleccionar método de pago - Nequi";
    private static final String MENSAJE_CAPTURA_7 = "Validar mensaje sin cuenta de Nequi";
    private static final String MENSAJE_CAPTURA_8 =
            "Seleccionar método de pago - Tarjeta Débito o Crédito";
    private static final String MENSAJE_CAPTURA_9 =
            "Validar URL del método de pago - Tarjeta Débito o Crédito";
    private static final String MENSAJE_CAPTURA_10 = "Validar redirección a enlace de pago ecarrier";
    private static final String MENSAJE_CAPTURA_11 = "Seleccionar método de pago - PSE";
    private static final String MENSAJE_CAPTURA_12 = "Validar URL del método de pago - PSE";
    private static final String MENSAJE_CAPTURA_13 =
            "Validar redirección a enlace de pago PSE  ecarrier";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(PAQ_TODO_INCLUIDO));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withAnyTextContains(AHORRA_Y_APROVECHA_MAXIMO));

        // Ahora: espera *hasta que exista* el botón en la última burbuja y haz clic allí
        actor.attemptsTo(EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(40));


        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(PAQUETE_2GB_7D));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);


        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withAnyTextContains(CONTINUAR_COMPRA, COMPRAR_PAQ_PREPAGO)
        );


        if (!Presence.of(BTN_COMPRAR_PAQ_PREPAGO).viewedBy(actor).resolveAll().isEmpty()) {
            actor.attemptsTo(
                    ClickTextoQueContengaX.elTextoContiene(COMPRAR_PAQ_PREPAGO),
                    WaitFor.aTime(2000));
        }


        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_4);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_4);

        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(CONTINUAR_COMPRA),
                WaitForResponse.withText(MEDIOS_DE_PAGO),
                ValidarTextoQueContengaX.elTextoContiene(SALDO_DISPONIBLE),
                ValidarTextoQueContengaX.elTextoContiene(PUEDES_COMPRAR_SALDO));


        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_5);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_5);

        actor.attemptsTo(
                Click.on(BTN_MEDIOS_DE_PAGO),
                WaitForResponse.withText(USA_TU_SALDO),
                ValidarTexto.validarTexto(USA_TU_SALDO),
                ValidarTexto.validarTexto(NEQUI),
                ValidarTexto.validarTexto(TARJETA_DEBITO_CREDITO),
                ValidarTexto.validarTexto(PSE),
                ClickTextoQueContengaX.elTextoContiene(NEQUI));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_6);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_6);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withAnyTextContains(MENU_PRINCIPAL, SMS_NEQUI),
                ValidarTextoQueContengaX.elTextoContiene(SMS_NEQUI),
                ValidarTextoQueContengaX.elTextoContiene(PAGAR_CON_OTRO_NEQUI));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_7);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_7);

        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(PAGAR_CON_OTRO_MEDIO),
                WaitFor.aTime(3000),
                Click.on(BTN_MEDIOS_DE_PAGO),
                ClickTextoQueContengaX.elTextoContiene(TARJETA_DEBITO_CREDITO));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_8);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_8);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withTextContains(HAZ_CLICK_ENLACE));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_9);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_9);

        // Extraer URL del mensaje
        String mensaje =
                WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .filter(text -> text.contains("clro.co/TC"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró mensaje con URL de pago."));

        String urlExtraida = ExtraerURL.desdeTexto(mensaje);
        UtilidadesAndroid.abrirLinkEnNavegador("http://" + urlExtraida);

        actor.attemptsTo(
                WaitForResponse.withText(BIENVENIDO),
                ValidarTextoQueContengaX.elTextoContiene(BIENVENIDO),
                ValidarTextoQueContengaX.elTextoContiene(COMO_PROTEGEMOS_INICIO_SESION),
                ValidarTextoQueContengaX.elTextoContiene(CONTINUAR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_10);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_10);

        actor.attemptsTo(
                Atras.irAtras(),
                ScrollInicio.scrollUnaVista(),
                ScrollHastaTexto.conTexto(MEDIOS_DE_PAGO),
                Click.on(BTN_MEDIOS_DE_PAGO),
                ClickTextoQueContengaX.elTextoContiene(PSE));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_11);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_11);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitFor.aTime(18000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_12);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_12);

        // Extraer URL del mensaje
        String mensajepse =
                WhatsAppPage.LBL_MENSAJES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .filter(text -> text.contains("clro.co/PSE"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró mensaje con URL de pago."));

        String urlExtraidapse = ExtraerURL.desdeTexto(mensajepse);
        UtilidadesAndroid.abrirLinkEnNavegador("http://" + urlExtraidapse);

        actor.attemptsTo(
                WaitForResponse.withText(BIENVENIDO),
                ValidarTextoQueContengaX.elTextoContiene(BIENVENIDO),
                ValidarTextoQueContengaX.elTextoContiene(COMO_PROTEGEMOS_INICIO_SESION),
                ValidarTextoQueContengaX.elTextoContiene(CONTINUAR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_13);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_13);

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );
    }


    public static Performable validarPagoPaquete() {
        return instrumented(ValidarPagoPaquete.class);
    }
}
