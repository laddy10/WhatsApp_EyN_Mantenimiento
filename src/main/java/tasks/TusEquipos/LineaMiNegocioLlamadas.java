package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static utils.Constantes.*;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.scroll.ScrollPersonalizado;
import interactions.wait.WaitFor;
import interactions.wait.WaitForResponse;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import utils.EvidenciaUtils;

public class LineaMiNegocioLlamadas implements Task {

    private static final String paso1 = "Seleccionar Linea de mi negocio";
    private static final String paso2 = "Validar pregunta en cual servicio necesitas ayuda";
    private static final String paso3 = "Seleccionar Llamadas";
    private static final String paso4 = "Validar mensaje diagnostico del servicio";
    private static final String paso5 = "Confirmar continuar con diagnostico";
    private static final String paso6 = "Validar texto Te recomendamos seguir este soporte con el paso a paso";
    private static final String paso7 = "Validar imagen de Configura tu punto de acceso";
    private static final String paso8 = "Validar imagen configura tu modo de red";
    private static final String paso9 = "Validar mensaje Ves fue sencillo seguir cada uno de los pasos";
    private static final String paso10 = "Seleccionar opcion SI";
    private static final String paso11 = "Validar mensaje de Exelente ayuda y Menu principal";
    private static final String paso12 = "Seleccionar opcion NO";
    private static final String paso13 = "Validar opcion contactar a un asesor y dar clic en NO";


    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(LINEA_MI_NEGOCIO),
                WaitFor.aTime(1000)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForResponse.withText(EN_CUAL_SERVICIO),
                ValidarTextoQueContengaX.elTextoContiene(EN_CUAL_SERVICIO),
                ValidarTextoQueContengaX.elTextoContiene(LLAMADAS),
                ValidarTextoQueContengaX.elTextoContiene(OTROS)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(LLAMADAS)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForResponse.withText(DIAGNOSTICO_SERVICIO),
                ValidarTextoQueContengaX.elTextoContiene(DIAGNOSTICO_SERVICIO),
                ValidarTextoQueContengaX.elTextoContiene(DESEAS_CONTINUAR)
        );
        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                Enter.theValue("1").into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR)
        );
        EvidenciaUtils.registrarCaptura(paso5);

        actor.attemptsTo(WaitFor.aTime(20000));
        actor.attemptsTo(ScrollPersonalizado.scroll(872, 522, 872, 1825));

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(
                        "Te recomendamos seguir este soporte con el paso a paso")
        );
        EvidenciaUtils.registrarCaptura(paso6);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(IMG_CONFIGUR_PUNTO_ACCESO)
        );
        EvidenciaUtils.registrarCaptura(paso7);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BOTON_ATRAS)
        );

        actor.attemptsTo(
                WaitFor.aTime(2000));
        actor.attemptsTo(ScrollPersonalizado.scroll(850, 1932, 850, 465));
        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(IMG_CONFIGURA_MODO_RED)
        );

        EvidenciaUtils.registrarCaptura(paso8);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BOTON_ATRAS)
        );
        actor.attemptsTo(
                WaitForResponse.withText(FUE_SENCILLO),
                ValidarTextoQueContengaX.elTextoContiene(FUE_SENCILLO),
                ValidarTextoQueContengaX.elTextoContiene(SE_RESOLVIO_FALLA)
        );
        EvidenciaUtils.registrarCaptura(paso9);

        actor.attemptsTo(
                Click.on(BTN_SI2)
        );
        EvidenciaUtils.registrarCaptura(paso10);

        actor.attemptsTo(
                WaitForResponse.withText(EXCELENTE_AYUDA),
                ValidarTextoQueContengaX.elTextoContiene(EXCELENTE_AYUDA),
                ValidarTextoQueContengaX.elTextoContiene(MENU_PRINCIPAL)
        );
        EvidenciaUtils.registrarCaptura(paso11);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BTN_NO)
        );

        EvidenciaUtils.registrarCaptura(paso12);

        actor.attemptsTo(
                WaitForResponse.withText(DESEAS_CONTACTAR_ASESOR),
                Click.on(BTN_NO2),
                WaitFor.aTime(2000),
                WaitForResponse.withText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso13);
    }

    public static Performable lineaMiNegocioLlamadas() {
        return instrumented(LineaMiNegocioLlamadas.class);
    }
}