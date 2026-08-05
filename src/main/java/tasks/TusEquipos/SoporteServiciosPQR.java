package tasks.TusEquipos;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import interactions.scroll.ScrollPersonalizado;
import interactions.wait.WaitFor;
import interactions.wait.WaitForElement;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

public class SoporteServiciosPQR implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion de Soporte y servicios";
    private static final String paso3 = "Seleccionar Estado de servicios";
    private static final String paso4 = "Seleccionar ver PQR";
    private static final String paso5 = "Validar redireccionamineto  Peticiones, Quejas y Recursos";
    private static final String paso6 = "Seleccionar finalizar chat";
    private static final String paso7 = "Validar mensajes luego de finalizar el chat";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SOPORTE_SERVICIOS, 15),
                Click.on(BTN_SOPORTE_SERVICIOS),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SELECCIONA, 15),
                Click.on(BTN_SELECCIONA_ESTADO_PQR),
                WaitForElement.waitFor(BTN_TUS_PQR_RADICADOS_DESCRIPCION, 15),
                Click.on(BTN_TUS_PQR_RADICADOS),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(ESTADO_RECLAMOS_QUEJAS_RADICADOS),
                Click.on(BTN_VER_PQR)
        );

        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(PETICIONES_QUEJAS_RECURSOS,  15)
        );

        EvidenciaUtils.registrarCaptura(paso5);

        actor.attemptsTo(
                Atras.irAtras(),
                WaitForTextContains.withTextContains(MENU_PRINCIPAL)
        );
        EvidenciaUtils.registrarCaptura(paso6);

    }

    public static Performable soporteServiciosPQR() {
            return instrumented(SoporteServiciosPQR.class);
    }
}