package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Click.ClickTextoQueContengaX;
import interactions.wait.WaitForTextContains;

import java.util.Arrays;
import java.util.List;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import questions.TextoQueContengaX;

public class ValidarTextoErrorYLimpiarChat implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        // 1. Validar si realmente quedó pendiente la política de tratamiento de datos
        boolean politicaTratamientoVisible =
                TextoQueContengaX.verificarTexto("¿Autorizas y aceptas?")
                        .answeredBy(actor);

        boolean noAutorizoVisible =
                TextoQueContengaX.verificarTexto("No autorizo")
                        .answeredBy(actor);

        if (politicaTratamientoVisible && noAutorizoVisible) {

            // Seleccionar "No autorizo"
            actor.attemptsTo(
                    ClickTextoQueContengaX.elTextoContiene("No autorizo")
            );

            // Esperar respuesta del bot después de rechazar la autorización
            actor.attemptsTo(
                    WaitForTextContains.withAnyTextContains(
                            50,
                            "Si no autorizas"
                    )
            );

            // Cerrar la conversación y vaciar el chat
            actor.attemptsTo(
                    SalirConversacion.salir(),
                    Click.on(BTN_MAS_OPCIONES),
                    ClickTextoQueContengaX.elTextoContiene(MAS),
                    ClickTextoQueContengaX.elTextoContiene(VACIAR_CHAT),
                    Click.on(BTN_VACIAR_CHAT)
            );

            return;
        }

        // 2. Manejo de errores y conversaciones residuales conocidas
        List<String> mensajesError =
                Arrays.asList(
                        "Por favor ingrese el número de opción",
                        "¡Lo siento! No entendí lo que escribiste",
                        "No entendí tu mensaje.",
                        "no entendí tu mensaje",
                        "No logré entender",
                        "Lo siento",
                        "opción que ingresaste no es válida",
                        "te mostré arriba",
                        "Te recomendamos seguir este soporte",
                        "Se resolvió la falla",
                        "Menú principal",
                        "Para continuar debes seleccionar alguna de las opciones mostradas anteriormente",
                        "Lo siento, tu respuesta no es válida",
                        "Deseas continuar",
                        "estos servicios necesitas ayuda"
                );

        boolean textoErrorDetectado =
                mensajesError.stream()
                        .anyMatch(
                                msg ->
                                        TextoQueContengaX
                                                .verificarTexto(msg)
                                                .answeredBy(actor)
                        );

        if (textoErrorDetectado) {

            actor.attemptsTo(
                    SalirConversacion.salir(),
                    Click.on(BTN_MAS_OPCIONES),
                    ClickTextoQueContengaX.elTextoContiene(MAS),
                    ClickTextoQueContengaX.elTextoContiene(VACIAR_CHAT),
                    Click.on(BTN_VACIAR_CHAT)
            );
        }
    }

    public static ValidarTextoErrorYLimpiarChat validarYLimpiar() {
        return instrumented(ValidarTextoErrorYLimpiarChat.class);
    }
}