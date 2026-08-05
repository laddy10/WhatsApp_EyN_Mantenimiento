package tasks.OtrasOpciones;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.LBL_MENSAJES;
import static utils.Constantes.*;
import static utils.ConstantesPost.CLARO_TE_ESCUCHA;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tasks.ExtraerURL;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

public class TusPQRSRadicados implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(TUS_PQR_RADICADOS_2));

        CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar Tus PQR radicados");
        ReportHooks.registrarPaso("Seleccionar Tus PQR radicados");

        actor.attemptsTo(
                ClickElementByText.clickElementByText(ENVIAR),
                WaitForTextContains.withAnyTextContains(ESTADO_PETICION));

        CapturaDePantallaMovil.tomarCapturaPantalla("Valida URL petición");
        ReportHooks.registrarPaso("Valida URL petición");

        String mensaje =
                LBL_MENSAJES.resolveAllFor(actor).stream()
                        .map(WebElementFacade::getText)
                        .filter(text -> text.contains("yoiz.me") || text.contains("clro.co"))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("No se encontró URL de pago."));

        String urlExtraida = ExtraerURL.desdeTexto(mensaje);

        UtilidadesAndroid.abrirLinkEnNavegador(urlExtraida);

        actor.attemptsTo(
                WaitForResponse.withAnyText(PERSONAS),
                ValidarTextoQueContengaX.elTextoContiene(CLARO_TE_ESCUCHA));

        CapturaDePantallaMovil.tomarCapturaPantalla("Direccionamiento Claro te escucha");
        ReportHooks.registrarPaso("Direccionamiento Claro te escucha");

        actor.attemptsTo(Atras.irAtras(), SalirConversacion.salir());
    }

    public static Performable tusPQRSRadicados() {
        return instrumented(TusPQRSRadicados.class);
    }
}
