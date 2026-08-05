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
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class PaqDeVoz implements Task {

    private static final String MENSAJE_CAPTURA = "Seleccionar el Paq. de Voz y enviar";
    private static final String MENSAJE_CAPTURA_2 =
            "Ingresar al boton Selecciona para validar los paquetes de voz";
    private static final String MENSAJE_CAPTURA_3 = "Se verifican los Paq. de Voz disponibles";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitForResponse.withText(PAQ_DE_VOZ), ClickTextoQueContengaX.elTextoContiene(PAQ_DE_VOZ));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA);

        actor.attemptsTo(Click.on(BTN_ENVIAR_2), WaitFor.aTime(10000));

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_2);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_2);

        actor.attemptsTo(
                EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20)
                // Click.on(BTN_SELECCIONA_PQ_TODO_INCLUIDO),
        );

        CapturaDePantallaMovil.tomarCapturaPantalla(MENSAJE_CAPTURA_3);
        ReportHooks.registrarPaso(MENSAJE_CAPTURA_3);

        actor.attemptsTo(
                ValidarTexto.validarTexto(PAQ_VOZ_2500_1D_PRECIO),
                ValidarTexto.validarTexto(PAQ_VOZ_2500_1D_DESC),
                ValidarTexto.validarTexto(PAQ_VOZ_3000_2D_PRECIO),
                ValidarTexto.validarTexto(PAQ_VOZ_3000_2D_DESC),
                ValidarTexto.validarTexto(PAQ_VOZ_17500_20D_PRECIO),
                ValidarTexto.validarTexto(PAQ_VOZ_17500_20D_DESC));

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );
    }

    public static Performable validarPaqDeVoz() {
        return instrumented(PaqDeVoz.class);
    }
}
