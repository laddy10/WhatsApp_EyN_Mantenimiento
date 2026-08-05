package tasks.TusEquipos;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static utils.Constantes.*;
import static utils.ConstantesPost.*;

import hooks.ReportHooks;
import interactions.Click.ClickTextoQueContengaX;
import interactions.Validaciones.ValidarTextoQueContengaX;
import interactions.comunes.Atras;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import tasks.SalirConversacion;
import utils.CapturaDePantallaMovil;

public class MenuTusEquipos implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                ClickTextoQueContengaX.elTextoContiene(SELECCIONA),
                ValidarTextoQueContengaX.elTextoContiene(REGISTRA_TU_EQUIPO),
                ValidarTextoQueContengaX.elTextoContiene(TUS_EQUIPOS_ASOCIADOS),
                ValidarTextoQueContengaX.elTextoContiene(TUS_EQUIPOS_EN_SOPORTE),
                ValidarTextoQueContengaX.elTextoContiene(REPORTE_ROBO_PERDIDA_2));

        CapturaDePantallaMovil.tomarCapturaPantalla("Validar menu completo");
        ReportHooks.registrarPaso("Validar menu completo");

        actor.attemptsTo(Atras.irAtras(), SalirConversacion.salir());
    }

    public static Performable menuTusEquipos() {
        return instrumented(MenuTusEquipos.class);
    }
}
