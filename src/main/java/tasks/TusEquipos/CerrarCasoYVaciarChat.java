package tasks.TusEquipos;

import interactions.Click.ClickTextoQueContengaX;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.questions.Presence;
import tasks.SalirConversacion;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

public class CerrarCasoYVaciarChat implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        boolean existeMensajeHola = !Presence
                .of(ME_NECESITAS_ESCRIBE_HOLA)
                .viewedBy(actor)
                .resolveAll()
                .isEmpty();

        if (!existeMensajeHola) {
            actor.attemptsTo(SalirConversacion.salir());
        }

        actor.attemptsTo(
                Click.on(BTN_MAS_OPCIONES),
                ClickTextoQueContengaX.elTextoContiene(MAS),
                ClickTextoQueContengaX.elTextoContiene(VACIAR_CHAT),
                Click.on(BTN_VACIAR_CHAT)
        );
    }

    public static Performable cerrarCasoYVaciarChat() {
        return instrumented(CerrarCasoYVaciarChat.class);
    }
}