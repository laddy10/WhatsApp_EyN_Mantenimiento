package tasks.TusEquipos;

import interactions.Click.ClickElementByText;
import interactions.Click.ClickPrimerTextoDisponible;
import interactions.Click.ManejarSelectorDeApp;
import interactions.Validaciones.ValidarCualquieraDeLosTextos;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_CERRAR;
import static utils.Constantes.*;

import interactions.Click.ClickElementByText;
import interactions.wait.WaitFor;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.*;
import static userinterfaces.WhatsAppPage.BTN_ENVIAR_MENU;
import static utils.Constantes.*;

public class RoamingInternacional implements Task {

    private static final String paso1 = "Click en Menú principal";
    private static final String paso2 = "Seleccionar la opcion Roaming internacional";
    private static final String paso3 = "Validar direccionamiento Mi Claro";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ClickElementByText.clickElementByText(MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                WaitForTextContains.withTextContains(ROAMING_INTERNACIONAL, 30),
                Click.on(BTN_ROAMING_INTERNACIONAL),
                Click.on(BTN_ENVIAR_MENU),
                WaitFor.aTime(2000)
        );

        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                WaitForTextContains.withAnyTextContains(GESTIONAR_ROAMING, COMPRAR_ROAMING_2),
                ClickPrimerTextoDisponible.entre(GESTIONAR_ROAMING, COMPRAR_ROAMING_2),
                ManejarSelectorDeApp.siAparece(),
                
                // Espera flexible: Si carga la app puede pedir sesión o entrar directo
                WaitForTextContains.withAnyTextContains(MUNDO_CLARO, INICIAR_SESION),
                WaitFor.aTime(4000),
                
                // Validación flexible: Cualquiera de los dos textos es una validación exitosa de redireccionamiento
                ValidarCualquieraDeLosTextos.contieneAlguno(MUNDO_CLARO, INICIAR_SESION)
        );

        EvidenciaUtils.registrarCaptura(paso3);


    }

    public static Performable roamingInternacional() {
        return instrumented(RoamingInternacional.class);
    }
}
