package tasks.HazTusRecargas;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickElementByText;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTexto;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.EsperarYClickSeleccionaEnUltimoMensaje;
import interactions.wait.WaitForResponse;

import java.util.List;

import models.User;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.CapturaDePantallaMovil;
import utils.TestDataProvider;

public class SeleccionarValorRecargas implements Task {

    private final User user = TestDataProvider.getRealUser();
    private final int MAX_INTENTOS = 3;

    @Override
    public <T extends Actor> void performAs(T actor) {

        boolean paqueteEncontrado = false;
        int intentos = 0;

        actor.attemptsTo(ClickElementByText.clickElementByText(SELECCIONA));

        String valorBuscado = normalizarTexto(user.getValor());

        while (!paqueteEncontrado && intentos < MAX_INTENTOS) {
            intentos++;

            List<WebElementFacade> opciones = LBL_SIGNO_PRECIO.resolveAllFor(actor);

            for (WebElementFacade opcion : opciones) {
                String texto = normalizarTexto(opcion.getText());

                if (texto.contains(valorBuscado)) {
                    actor.attemptsTo(
                            ClickTextoQueContengaX.elTextoContiene(user.getValor()),
                            WaitForResponse.withText(ENVIAR2));

                    CapturaDePantallaMovil.tomarCapturaPantalla("Seleccionar el valor de la recarga");
                    ReportHooks.registrarPaso("Seleccionar el valor de la recarga");


                    actor.attemptsTo(
                            ClickTextoQueContengaX.elTextoContiene(ENVIAR2),
                            WaitForResponse.withAnyText(MEDIOS_DE_PAGO, CONTINUAR_RECARGA)
                    );


                    CapturaDePantallaMovil.tomarCapturaPantalla("Se habilita el boton 'Medios de pago o 'Continuar recarga'.");
                    ReportHooks.registrarPaso("Se habilita el boton 'Medios de pago o 'Continuar recarga'.");


                    CapturaDePantallaMovil.tomarCapturaPantalla("Se habilita el boton 'Medios de pago o 'Continuar recarga'.");
                    ReportHooks.registrarPaso("Se habilita el boton 'Medios de pago o 'Continuar recarga'.");

                    paqueteEncontrado = true;
                    break;
                }
            }

            if (!paqueteEncontrado) {
                List<WebElementFacade> masOpciones = LBL_MAS_OPCIONES.resolveAllFor(actor);

                if (!masOpciones.isEmpty()) {
                    actor.attemptsTo(Click.on(LBL_MAS_OPCIONES), WaitForResponse.withText(ENVIAR2));

                    CapturaDePantallaMovil.tomarCapturaPantalla(
                            "No se encontro el valor, dar clic en 'Mas opciones'");
                    ReportHooks.registrarPaso("No se encontro el valor, dar clic en 'Mas opciones'");

                    actor.attemptsTo(
                            ClickTextoQueContengaX.elTextoContiene(ENVIAR2),
                            WaitForResponse.withText(OTRO_VALOR_RECARGA),
                            ValidarTexto.validarTexto(MAS_OPCIONES));

                    CapturaDePantallaMovil.tomarCapturaPantalla(
                            "Validar mensaje que contenga el botón Selecciona");
                    ReportHooks.registrarPaso("Validar mensaje que contenga el botón Selecciona");

                    actor.attemptsTo(EsperarYClickSeleccionaEnUltimoMensaje.conTimeout(20));

                } else {
                    throw new RuntimeException(
                            "No se encontró el valor y no hay más opciones disponibles: " + user.getValor());
                }
            }
        }

        if (!paqueteEncontrado) {
            throw new RuntimeException(
                    "No se encontró el valor tras " + MAX_INTENTOS + " intentos: " + user.getValor());
        }
    }

    private String normalizarTexto(String texto) {
        return texto.toLowerCase().replaceAll("\\s+", " ").trim();
    }

    public static Performable seleccionarValorRecargas() {
        return instrumented(SeleccionarValorRecargas.class);
    }
}
