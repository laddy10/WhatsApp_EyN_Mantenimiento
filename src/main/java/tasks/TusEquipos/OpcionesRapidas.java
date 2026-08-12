package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;

import interactions.Click.ClickElementByText;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.wait.WaitForResponse;
import interactions.wait.WaitForTextContains;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import utils.EvidenciaUtils;

public class OpcionesRapidas implements Task {

    private static final String paso1 = "Validar opciones rapidas que te pueden ayudar";
    private static final String paso2 = "Validar botones soporte tecnico, detalle plan y consumo";
    private static final String paso3 = "Click en Soporte tecnico";
    private static final String paso4 = "Validar respuesta soporte tecnico";

    @Override
    public <T extends Actor> void performAs(T actor) {


        actor.attemptsTo(
                WaitForTextContains.withAnyTextContains(OPCIONES_RAPIDAS),
                ValidarTextoQueContengaX.elTextoContiene(OPCIONES_RAPIDAS)
        );
        EvidenciaUtils.registrarCaptura(paso1);

        actor.attemptsTo(
                ValidarTextoQueContengaX.elTextoContiene(SOPORTE_TECNICO),
                ValidarTextoQueContengaX.elTextoContiene(CONSULTA_TU_CONSUMO),
                ValidarTextoQueContengaX.elTextoContiene(ELIGE_UNA_OPCION)
        );
        EvidenciaUtils.registrarCaptura(paso2);

        actor.attemptsTo(
                ClickElementByText.clickElementByText(SOPORTE_TECNICO)
        );
        EvidenciaUtils.registrarCaptura(paso3);

        actor.attemptsTo(WaitForResponse.withText(SOPORTE_TECNICO));
        EvidenciaUtils.registrarCaptura(paso4);

        
    }

    public static Performable opcionesRapidas() {
        return instrumented(OpcionesRapidas.class);
    }
}