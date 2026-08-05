package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isNotVisible;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPostpagoPage.BTN_ACCEDER_CLARO_MUSICA;
import static utils.Constantes.*;
import static utils.Constantes.URL_CLARO_MUSICA;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;

import java.util.List;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import questions.ValidarElemento;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

public class ValidarClaroMusica implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        CapturaDePantallaMovil.tomarCapturaPantalla("Validar mensaje con URL de claro musica");
        ReportHooks.registrarPaso("Validar mensaje con URL de claro musica");

        actor.attemptsTo(ValidarTextoQueContengaX.elTextoContiene(CLARO_MUSICA));

        UtilidadesAndroid.abrirLinkEnNavegador(URL_CLARO_MUSICA);

        actor.attemptsTo(
                WaitForResponse.withAnyText(
                        INSTALAR, ACEPTAR_Y_CONTINUAR, ESCUCHA_GRATIS, ACCEDER_CLARO_MUSICA));

        List<WebElementFacade> btninstalar = BTN_INSTALAR.resolveAllFor(actor);

        if (!btninstalar.isEmpty()) {

            CapturaDePantallaMovil.tomarCapturaPantalla(
                    "La app no se encuentra instalada, se procede a instalarla.");
            ReportHooks.registrarPaso("La app no se encuentra instalada, se procede a instalarla.");

            actor.attemptsTo(
                    Click.on(BTN_INSTALAR),
                    WaitForResponse.withText(DESINSTALAR),
                    WaitUntil.the(BTN_CANCELAR, isNotVisible()));

            CapturaDePantallaMovil.tomarCapturaPantalla("Se da clic en continuar para abrir la app");
            ReportHooks.registrarPaso("Se da clic en continuar para abrir la app");

            actor.attemptsTo(
                    ClickElementByText.clickElementByText(CONTINUAR),
                    WaitForResponse.withAnyText(BIENVENIDO_CLARO_MUSICA));
        }

        List<WebElementFacade> lblbienvenido = TXT_BIENVENIDO_CLARO.resolveAllFor(actor);

        if (!lblbienvenido.isEmpty()) {
            actor.should(seeThat(ValidarElemento.esVisible(TXT_BIENVENIDO_CLARO)));

            CapturaDePantallaMovil.tomarCapturaPantalla("Se valida el ingreso a Claro Musica");
            ReportHooks.registrarPaso("Se valida el ingreso a Claro Musica");

            actor.attemptsTo(
                    ClickElementByText.clickElementByText(ACEPTAR_Y_CONTINUAR),
                    WaitForResponse.withText(ESCUCHA_GRATIS));
        }

        List<WebElementFacade> txtpermisonotificacion = TXT_PERMISO_NOTIFICACION.resolveAllFor(actor);
        if (!txtpermisonotificacion.isEmpty()) {
            actor.attemptsTo(Click.on(BTN_NO_PERMITIR));
        }

        List<WebElementFacade> btnaccederclaromusica = BTN_ACCEDER_CLARO_MUSICA.resolveAllFor(actor);
        if (!btnaccederclaromusica.isEmpty()) {
            CapturaDePantallaMovil.tomarCapturaPantalla("Acceder a Claro música");
            ReportHooks.registrarPaso("Acceder a Claro música");

            actor.attemptsTo(
                    Click.on(BTN_ACCEDER_CLARO_MUSICA),
                    WaitForResponse.withText(ESCUCHA_GRATIS),
                    WaitFor.aTime(3000));
        }

        CapturaDePantallaMovil.tomarCapturaPantalla("Se ingresa a Escucha gratis");
        ReportHooks.registrarPaso("Se ingresa a Escucha gratis");

        actor.attemptsTo(
                ClickElementByText.clickElementByText(ESCUCHA_GRATIS),
                WaitForResponse.withText(INGRESAR_CON_NUMERO_CLARO),
                ValidarTexto.validarTexto(INGRESAR_CON_NUMERO_CLARO));

        CapturaDePantallaMovil.tomarCapturaPantalla("Ingresar correctamente a Claro musica");
        ReportHooks.registrarPaso("Ingresar correctamente a Claro musica");

        if (!btnaccederclaromusica.isEmpty()) {
            actor.attemptsTo(Atras.irAtras());
        }

        actor.attemptsTo(
                Atras.irAtras(),
                Atras.irAtras(),
                SalirConversacion.salir()
        );
    }

    public static Performable validarClaroMusica() {
        return instrumented(ValidarClaroMusica.class);
    }
}
