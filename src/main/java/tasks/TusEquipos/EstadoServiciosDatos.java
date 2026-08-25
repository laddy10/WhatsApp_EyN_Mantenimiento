package tasks.TusEquipos;

import interactions.ClicEnModoRedSeguro;
import interactions.ClicEnPuntoAccesoSeguro;
import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.scroll.ScrollInicio;
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

public class EstadoServiciosDatos implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion de Soporte y servicios";
    private static final String paso3 = "Seleccionar Estado de servicios";
    private static final String paso4 = "Seleccionar linea de mi negocio";
    private static final String paso5 = "Selecciona la Opcion Datos";
    private static final String paso6 = "Seleccionar opción 1";
    private static final String paso7 = "Validar recomendacion soporte paso a paso";
    private static final String paso8 = "Validar imagen Configura tu punto de acceso";
    private static final String paso9 = "Validar imagen Configura tu modo de red";
    private static final String paso10 = "Validar mensaje fue sencillo";
    private static final String paso11 = "Seleccionar opcion SI";
    private static final String paso12 = "Validar mensaje excelente ayuda";
    private static final String paso13 = "Seleccionar opcion NO";
    private static final String paso14 = "Validar opcion contactar asesor y dar click en NO";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SOPORTE_SERVICIOS, 30),
                Click.on(BTN_SOPORTE_SERVICIOS),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(SELECCIONA, 30),
                Click.on(BTN_SELECCIONA_ESTADO_PQR),
                WaitForElement.waitFor(BTN_ESTADO_SERVICIOS_DESCRIPCION, 30),
                Click.on(BTN_ESTADO_SERVICIOS),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(LINEA_MI_NEGOCIO, 30),
                Click.on(BTN_LINEA_MI_NEGOCIO)
        );
        EvidenciaUtils.registrarCaptura(paso4);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(DATOS, 30),
                Click.on(BTN_DATOS)
        );
        EvidenciaUtils.registrarCaptura(paso5);

        actor.attemptsTo(
                Enter.theValue("1").into(TXT_ENVIAR_MENSAJE),
                Click.on(BTN_ENVIAR),
                WaitFor.aTime(2000)
        );
        EvidenciaUtils.registrarCaptura(paso6);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(TE_RECOMENDAMOS_SOPORTE, 30)
        );
        // actor.attemptsTo(ScrollPersonalizado.scroll(872, 522, 872, 1825));
        actor.attemptsTo(
                ScrollInicio.scrollUnaVista(),
                ValidarTextoQueContengaX.elTextoContiene(TE_RECOMENDAMOS_SOPORTE)
        );
        EvidenciaUtils.registrarCaptura(paso7);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                ClicEnPuntoAccesoSeguro.ejecutar()
        );
        EvidenciaUtils.registrarCaptura(paso8);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BOTON_ATRAS)
        );

        actor.attemptsTo(ScrollPersonalizado.scroll(850, 1932, 850, 465));

        actor.attemptsTo(
                WaitFor.aTime(2000),
                ClicEnModoRedSeguro.ejecutar()
        );
        EvidenciaUtils.registrarCaptura(paso9);

        actor.attemptsTo(
                WaitFor.aTime(2000),
                Click.on(BOTON_ATRAS)
        );

        actor.attemptsTo(
                WaitForTextContains.withTextContains(FUE_SENCILLO, 30),
                ValidarTextoQueContengaX.elTextoContiene(SE_RESOLVIO_FALLA)
        );
        EvidenciaUtils.registrarCaptura(paso10);

        actor.attemptsTo(
                Click.on(BTN_SI2)
        );
        EvidenciaUtils.registrarCaptura(paso11);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(EXCELENTE_AYUDA, 30),
                ValidarTextoQueContengaX.elTextoContiene(MENU_PRINCIPAL)
        );
        EvidenciaUtils.registrarCaptura(paso12);


    }

    public static Performable estadoServiciosDatos() {
        return instrumented(EstadoServiciosDatos.class);
    }
}