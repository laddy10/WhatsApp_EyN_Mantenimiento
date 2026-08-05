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
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class PaqDeDatos implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. de datos y enviar";
    private static final String MENSAJE_CAPTURA_2 =
            "Ingresar al boton Selecciona para validar los paquetes de datos";
    private static final String MENSAJE_CAPTURA_3 = "Se verifican los Paq. de datos disponibles";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(PAQ_DE_DATOS),
                ClickTextoQueContengaX.elTextoContiene(PAQ_DE_DATOS));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(
                Click.on(BTN_ENVIAR_2),
                WaitForTextContains.withAnyTextContains(DISFRUTA_TUS_APP),
                WaitFor.aTime(5000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        actor.attemptsTo(
                EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20),
                WaitForResponse.withText(PAQ_DATOS_2000_1D_PRECIO));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

        actor.attemptsTo(
                ValidarTexto.validarTexto(PAQ_DATOS_2000_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_DATOS_2000_1D_DESC),
                ValidarTexto.validarTexto(PAQ_DATOS_3000_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_DATOS_3000_1D_DESC),
                ValidarTexto.validarTexto(PAQ_DATOS_5000_3D_PRECIO),
                ValidarTexto.validarTexto(PAQ_DATOS_5000_3D_DESC),
                ValidarTexto.validarTexto(PAQ_DATOS_6000_2H_PRECIO),
                ValidarTexto.validarTexto(PAQ_DATOS_6000_2H_DESC),
                ValidarTexto.validarTexto(PAQ_DATOS_8000_7D_PRECIO),
                ValidarTexto.validarTexto(PAQ_DATOS_8000_7D_DESC));

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );
    }

    public static Performable validarPaqDeDatos() {
        return instrumented(PaqDeDatos.class);
    }
}
