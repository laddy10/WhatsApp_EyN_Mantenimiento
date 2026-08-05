package hooks;

import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.model.TestStep;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.DataTable;
import java.util.Map;
import utils.GestorMetricas;

public class MetricasStepListener implements StepListener {

    @Override
    public void testFinished(TestOutcome result) {
        String nombreEscenario = result.getName(); // Ej. "Compra de paquete de datos postpago"

        // 1. Obtenemos SOLO los pasos principales (Given, When, Then del archivo .feature)
        for (TestStep paso : result.getTestSteps()) {
            
            String nombreFlujo = paso.getDescription();
            double tiempoSegundos = paso.getDuration() / 1000.0;
            String estado = paso.getResult().toString();
            
            // Ignorar pasos que duran 0 segundos
            if (tiempoSegundos > 0) {
                GestorMetricas.registrarMetricaAutomatica(nombreEscenario, nombreFlujo, tiempoSegundos, estado, "Android");
            }
        }

        // 2. Registramos el TIEMPO TOTAL de todo el escenario (la suma de todo)
        double tiempoTotalSegundos = result.getDuration() / 1000.0;
        String estadoFinal = result.getResult().toString();
        
        if (tiempoTotalSegundos > 0) {
            GestorMetricas.registrarMetricaAutomatica(nombreEscenario, "TIEMPO_TOTAL_ESCENARIO", tiempoTotalSegundos, estadoFinal, "Android");
        }
    }

    // --- Métodos obligatorios de la interfaz StepListener (Vacíos intencionalmente) ---

    @Override public void testSuiteStarted(Class<?> storyClass) {}
    @Override public void testSuiteStarted(Story story) {}
    @Override public void testSuiteFinished() {}
    @Override public void testStarted(String description) {}
    @Override public void testStarted(String description, String id) {}
    @Override public void testRetried() {}
    @Override public void stepStarted(ExecutedStepDescription description) {}
    @Override public void skippedStepStarted(ExecutedStepDescription description) {}
    @Override public void stepFinished() {}
    @Override public void stepIgnored() {}
    @Override public void stepPending() {}
    @Override public void stepPending(String message) {}
    @Override public void assumptionViolated(String message) {}
    @Override public void testRunFinished() {}
    @Override public void stepFailed(StepFailure failure) {}
    @Override public void lastStepFailed(StepFailure failure) {}
    public void testFinished(TestOutcome result, boolean isDataDriven, boolean stateExhausted) {}
    public void exampleFinished() {}
    public void exampleStarted(Map<String, String> data) {}
    @Override public void addNewExamplesFrom(DataTable table) {}
    @Override public void useExamplesFrom(DataTable table) {}
    @Override public void notifyScreenChange() {}
    @Override public void testIsManual() {}
    @Override public void testPending() {}
    @Override public void testSkipped() {}
    @Override public void testIgnored() {}
    @Override public void testFailed(TestOutcome result, Throwable cause) {}
}
