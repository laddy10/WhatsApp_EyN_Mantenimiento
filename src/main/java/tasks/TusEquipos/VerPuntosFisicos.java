package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.LBL_MENSAJES;
import static utils.Constantes.*;
import static utils.ConstantesPost.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.scroll.ScrollHastaTexto;
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

public class VerPuntosFisicos implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(ClickTextoQueContengaX.elTextoContiene(VER_PUNTOS_FISICOS));

        CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar Ver puntos fisicos");
        ReportHooks.registrarPaso("Seleccionar Ver puntos fisicos");

        actor.attemptsTo(WaitForTextContains.withAnyTextContains(CAV));

        CapturaDePantallaMovil.tomarCapturaPantalla("Abrir URL");
        ReportHooks.registrarPaso("Abrir URL");

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
                ValidarTextoQueContengaX.elTextoContiene(CENTROS_CLARO),
                ValidarTextoQueContengaX.elTextoContiene(CAVS_CLARO));

        CapturaDePantallaMovil.tomarCapturaPantalla("Direccionamiento Cavs Claro");
        ReportHooks.registrarPaso("Direccionamiento Cavs Claro");

        actor.attemptsTo(ScrollHastaTexto.conTexto("Mapa"));

        CapturaDePantallaMovil.tomarCapturaPantalla("Mapa Cavs Claro");
        ReportHooks.registrarPaso("Mapa Cavs Claro");

        actor.attemptsTo(Atras.irAtras(), SalirConversacion.salir());
    }

    public static Performable verPuntosFisicos() {
        return instrumented(VerPuntosFisicos.class);
    }
}
