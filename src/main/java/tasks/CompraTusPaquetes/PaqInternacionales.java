package tasks.CompraTusPaquetes;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_VER_PAQUETES;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.comunes.Atras;
import interactions.wait.*;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class PaqInternacionales implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. Internacionales y enviar";
    private static final String MENSAJE_CAPTURA_2 =
            "Validar opciones de paquetes internacionales disponibles";
    private static final String MENSAJE_CAPTURA_3 = "Seleccionar y validar Paq. LDI USA,CAN,PR,MEX";
    private static final String MENSAJE_CAPTURA_4 = "Validar paquetes LDI USA disponibles";
    private static final String MENSAJE_CAPTURA_5 = "Seleccionar y validar Paq. LDI Sin Fronteras";
    private static final String MENSAJE_CAPTURA_6 = "Validar paquetes LDI Sin Fronteras disponibles";
    private static final String MENSAJE_CAPTURA_7 = "Seleccionar y validar Paq. LDI Venezuela";
    private static final String MENSAJE_CAPTURA_8 = "Validar paquetes LDI Venezuela disponibles";
    private static final String MENSAJE_CAPTURA_9 = "Seleccionar y validar Paq. LDI Preferencial";
    private static final String MENSAJE_CAPTURA_10 = "Validar paquetes LDI Preferencial disponibles";
    private static final String MENSAJE_CAPTURA_11 = "Seleccionar y validar Paq. LDI Resto del Mundo";
    private static final String MENSAJE_CAPTURA_12 =
            "Validar paquetes LDI Resto del Mundo disponibles";
    private static final String MENSAJE_CAPTURA_13 = "Seleccionar y validar Paq. Roaming";
    private static final String MENSAJE_CAPTURA_14 = "Seleccionar Comprar Roaming y Ver paquetes";
    private static final String MENSAJE_CAPTURA_15 = "Validar paquetes Roaming disponibles";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(PAQ_INTERNACIONALES),
                ClickTextoQueContengaX.elTextoContiene(PAQ_INTERNACIONALES));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2), WaitForTextContains.withTextContains(LABEL_BTN_SELECCIONA));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        // Validar LDI USA,CAN,PR,MEX
        validarPaqueteLDI(
                actor,
                PAQ_LDI_USA_CAN_PR_MEX,
                MENSAJE_CAPTURA_3,
                MENSAJE_CAPTURA_4,
                PAQ_LDI_USA_60MIN_5900,
                PAQ_LDI_USA_120MIN_9900,
                PAQ_LDI_USA_240MIN_18900);

        // Validar LDI Sin Fronteras
        validarPaqueteLDI(
                actor,
                PAQ_LDI_SIN_FRONTERAS,
                MENSAJE_CAPTURA_5,
                MENSAJE_CAPTURA_6,
                PAQ_LDI_SF_50MIN_18900,
                PAQ_LDI_SF_100MIN_31900,
                PAQ_LDI_SF_200MIN_59900);

        // Validar LDI Venezuela
        validarPaqueteLDI(
                actor,
                PAQ_LDI_VENEZUELA,
                MENSAJE_CAPTURA_7,
                MENSAJE_CAPTURA_8,
                PAQ_LDI_VEN_12MIN_2900,
                PAQ_LDI_VEN_30MIN_6900);

        // Validar LDI Preferencial
        validarPaqueteLDI(
                actor,
                PAQ_LDI_PREFERENCIAL,
                MENSAJE_CAPTURA_9,
                MENSAJE_CAPTURA_10,
                PAQ_LDI_PREF_50MIN_23900,
                PAQ_LDI_PREF_100MIN_44900,
                PAQ_LDI_PREF_200MIN_79900);

        // Validar LDI Resto del Mundo
        validarPaqueteLDI(
                actor,
                PAQ_LDI_RESTO_MUNDO,
                MENSAJE_CAPTURA_11,
                MENSAJE_CAPTURA_12,
                PAQ_LDI_RM_50MIN_40900,
                PAQ_LDI_RM_100MIN_80900,
                PAQ_LDI_RM_200MIN_150900);

        // Validar Roaming
        actor.attemptsTo(
                Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
                ClickTextoQueContengaX.elTextoContiene(PAQ_ROAMING));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_13);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_13);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForResponse.withText(COMPRAR_ROAMING),
                ClickTextoQueContengaX.elTextoContiene(COMPRAR_ROAMING),
                WaitForResponse.withAnyText(VER_PAQUETES));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_14);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_14);

        actor.attemptsTo(
                Click.on(BTN_VER_PAQUETES),
                WaitForResponse.withText(PAQ_ROAMING_1D_1GB_11900),
                ValidarTexto.validarTexto(PAQ_ROAMING_1D_1GB_11900),
                ValidarTexto.validarTexto(PAQ_ROAMING_1D_1GB_DESC),
                ValidarTexto.validarTexto(PAQ_ROAMING_1D_2GB_21900),
                ValidarTexto.validarTexto(PAQ_ROAMING_1D_2GB_DESC));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_15);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_15);

        actor.attemptsTo(Atras.irAtras(), SalirConversacion.salir());
    }

    private <T extends Actor> void validarPaqueteLDI(
            T actor,
            String paquete,
            String capturaSeleccion,
            String capturaValidacion,
            String... paquetesValidar) {
        actor.attemptsTo(
                EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20),
                ClickTextoQueContengaX.elTextoContiene(paquete));

        CapturaDePantallaMovil.tomarCapturaPantalla(capturaSeleccion);
        ReportHooks.registrarPaso(capturaSeleccion);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withTextContains(ELIGE_COMPRA),
                EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20));

        CapturaDePantallaMovil.tomarCapturaPantalla(capturaValidacion);
        ReportHooks.registrarPaso(capturaValidacion);

        for (String paqueteValidar : paquetesValidar) {
            actor.attemptsTo(ValidarTexto.validarTexto(paqueteValidar));
        }

        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(MENU_ANTERIOR),
                Click.on(BTN_ENVIAR_2),
                WaitFor.aTime(6000));
    }

    public static Performable validarPaqInternacionales() {
        return instrumented(PaqInternacionales.class);
    }
}
