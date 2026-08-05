package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitFor;
import models.User;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.CapturaDePantallaMovil;
import utils.TestDataProvider;

public class SalirYReiniciarChat implements Task {

    private final User user = TestDataProvider.getRealUser();

    @Override
    public <T extends Actor> void performAs(T actor) {

        CapturaDePantallaMovil.tomarCapturaPantalla("Pantalla no válida detectada - Reiniciando chat");
        ReportHooks.registrarPaso("Pantalla no válida detectada - Se reinicia la conversación");

        actor.attemptsTo(
                SalirConversacion.salir(),
                WaitFor.aTime(1500),
                Click.on(BTN_MAS_OPCIONES),
                ClickTextoQueContengaX.elTextoContiene(MAS),
                ClickTextoQueContengaX.elTextoContiene(VACIAR_CHAT),
                Click.on(BTN_VACIAR_CHAT),
                WaitFor.aTime(1500),
                Enter.theValue(user.getSaludo()).into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR),
                WaitFor.aTime(2500));
    }

    public static Task ejecutar() {
        return instrumented(SalirYReiniciarChat.class);
    }
}
