package tasks.TodoSobreTuLinea;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;
import static utils.ConstantesPost.MI_CLARO_DRIVE;
import static utils.ConstantesPost.NEGOCIO;

import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.scroll.Scroll;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;
import utils.UtilidadesAndroid;

public class ValidarClaroDrive implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        CapturaDePantallaMovil.tomarCapturaPantalla("captura_pantalla");

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(CLARO_DRIVE),
                ValidarTextoQueContengaX.elTextoContiene(URL_CLARO_DRIVE));

        UtilidadesAndroid.abrirLinkEnNavegador("https://bit.ly/3oNvleC");

        actor.attemptsTo(
                WaitFor.aTime(15000),
                WaitForTextContains.withAnyTextContains(NEGOCIO),
                ValidarTextoQueContengaX.elTextoContiene(MI_CLARO_DRIVE),
                ValidarTextoQueContengaX.elTextoContiene(NEGOCIO));

        CapturaDePantallaMovil.tomarCapturaPantalla("captura_pantalla");

        actor.attemptsTo(Scroll.scrollUnaVista());

        CapturaDePantallaMovil.tomarCapturaPantalla("captura_pantalla");

        actor.attemptsTo(
                Atras.irAtras(),
                SalirConversacion.salir()
        );

        //  WordAppium.main();
    }

    public static Performable validarClaroDrive() {
        return instrumented(ValidarClaroDrive.class);
    }
}
