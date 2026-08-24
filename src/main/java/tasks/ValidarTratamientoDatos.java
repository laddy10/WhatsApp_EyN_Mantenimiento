package tasks;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static userinterfaces.WhatsAppPage.BTN_SI_AUTORIZO;
import static utils.Constantes.*;

import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForTextContains;
import interactions.wait.WaitForTextContainsWithTimeout;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import utils.EvidenciaUtils;

public class ValidarTratamientoDatos implements Task {

    private static final String paso1 = "Validar mensaje politica de tratamiento de datos";
    private static final String paso2 = "Validar link tratamiento de informacion";
    private static final String paso3 = "Validar pregunta autorizas y aceptas";
    private static final String paso4 = "Click en Si autorizo";

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(POLITICA_TRATAMIENTO)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(URL_TRATAMIENTO_EMPRESAS)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(AUTORIZAS_ACEPTAS)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(
                Click.on(BTN_SI_AUTORIZO),
                WaitForTextContains.withAnyTextContains(
                        OPCIONES_RAPIDAS,
                        SOPORTE_TECNICO,
                        DETALLE_DE_TU_PLAN,
                        CONSULTA_TU_CONSUMO,
                        ELIGE_UNA_OPCION,
                        MENU_PRINCIPAL)
        );

        EvidenciaUtils.registrarCaptura(paso4);
    }

    public static Performable validarTratamientoDatos() {
        return instrumented(ValidarTratamientoDatos.class);
    }
}