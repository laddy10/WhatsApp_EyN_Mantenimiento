package tasks.HazTusRecargas;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static tasks.CompraTusPaquetes.Constants_Paquetes.*;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.wait.EsperarYClickSeleccionaEnUltimoMensaje;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class ValidarMenuRecargas implements Task {

    private static final String MENSAJE_CAPTURA = "Validar valores de recargas disponibles";
    private static final String MENSAJE_CAPTURA_2 =
            "Seleccionar Más opciones para ver valores adicionales";
    private static final String MENSAJE_CAPTURA_3 = "Validar valores adicionales de recargas";
    private static final String MENSAJE_CAPTURA_4 = "Información validada correctamente";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(SELECCIONA), ClickElementByText.clickElementByText(SELECCIONA));

        // Validar valores principales de recarga
        actor.attemptsTo(
                ValidarTexto.validarTexto(RECARGA_1000),
                ValidarTexto.validarTexto(RECARGA_2000),
                ValidarTexto.validarTexto(RECARGA_3000),
                ValidarTexto.validarTexto(RECARGA_4000),
                ValidarTexto.validarTexto(RECARGA_5000),
                ValidarTexto.validarTexto(RECARGA_6000),
                ValidarTexto.validarTexto(RECARGA_7000),
                ValidarTexto.validarTexto(RECARGA_8000),
                ValidarTexto.validarTexto(MAS_OPCIONES));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        // Hacer clic en Más opciones
        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(MAS_OPCIONES));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitForTextContains.withAnyTextContains(RECARGATE));

        actor.attemptsTo(EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20));

        // Validar valores adicionales de recarga
        actor.attemptsTo(
                ValidarTexto.validarTexto(RECARGA_9000),
                ValidarTexto.validarTexto(RECARGA_10000),
                ValidarTexto.validarTexto(RECARGA_12000),
                ValidarTexto.validarTexto(RECARGA_15000),
                ValidarTexto.validarTexto(RECARGA_20000),
                ValidarTexto.validarTexto(RECARGA_30000),
                ValidarTexto.validarTexto(RECARGA_50000),
                ValidarTexto.validarTexto(RECARGA_70000),
                ValidarTexto.validarTexto(RECARGA_100000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

        // Seleccionar "Otro valor recarga"
        actor.attemptsTo(
                Atras.irAtras(),
                WaitForResponse.withText(DESEAS_RECARGAR_OTRO_VALOR),
                ValidarTextoQueContengaX.elTextoContiene(DESEAS_RECARGAR_OTRO_VALOR));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_4);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_4);

        actor.attemptsTo(
                SalirConversacion.salir()
        );
    }

    public static Performable validarMenuRecargas() {
        return instrumented(ValidarMenuRecargas.class);
    }
}
