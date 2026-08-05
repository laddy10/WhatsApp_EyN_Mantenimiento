package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;

public class IniciarChatClaro implements Task {

    private static final String paso1 = "Enviar saludo Hola al bot";
    private static final String paso2 = "Validar respuesta del bot Claro Empresas y Negocios";

    @Override
    public <T extends Actor> void performAs(T actor) {

        // 1️⃣ Escribir Hola y enviar
        actor.attemptsTo(
                Enter.theValue("Hola").into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        // 2️⃣ Esperar respuesta del bot
        actor.attemptsTo(
                WaitForTextContains.withAnyTextContains(
                        30,
                        "Claro Empresas y Negocios",
                        "Estoy aquí para ayudarte",
                        "¿Autorizas y aceptas?"
                )
        );

        // 3️⃣ Validar textos de la respuesta
        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(CLARO_EMPRESAS_NEGOCIOS),
                ValidarTextoQueContengaX.elTextoContiene(ESTOY_AQUI_PARA_AYUDARTE)
        );
        EvidenciaUtils.registrarCaptura(paso2);
    }

    public static Performable iniciarChatClaro() {
        return instrumented(IniciarChatClaro.class);
    }
}