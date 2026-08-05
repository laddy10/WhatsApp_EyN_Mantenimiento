package hooks;

import java.io.PrintWriter;
import java.io.StringWriter;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.model.Story;
import net.thucydides.core.model.TestOutcome;
import net.thucydides.core.steps.ExecutedStepDescription;
import net.thucydides.core.steps.StepFailure;
import net.thucydides.core.steps.StepListener;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;
import utils.ollama.FailureContext;
import utils.ollama.OllamaAnalyzer;

public class OllamaStepListener implements StepListener {

    private final OllamaAnalyzer analyzer;

    public OllamaStepListener() {
        this.analyzer = new OllamaAnalyzer();
    }

    @Override
    public void stepFailed(StepFailure failure) {
        try {
            if (Serenity.hasASessionVariableCalled("ollama_analyzed")) {
                return;
            }
            Serenity.setSessionVariable("ollama_analyzed").to(true);

            FailureContext context = buildFailureContext(failure);
            analyzeWithOllama(context);
        } catch (Exception e) {
            System.err.println(
                    "OllamaStepListener fallo, ignorando para no afectar el test: " + e.getMessage());
        }
    }

    private FailureContext buildFailureContext(StepFailure failure) {
        String testName = failure.getMessage();
        String stepDesc = failure.getDescription() != null ? failure.getDescription().getName() : "Unknown";
        Throwable error = failure.getException();
        if (error != null && error.getCause() != null) {
            error = error.getCause();
        }

        String pageSource = capturePageSourceWithRetry();
        String stackTrace = extractStackTrace(error);


        String locator = "N/A";
        String locatorType = "UNKNOWN";
        if (error != null && error.getMessage() != null) {
            if (error.getMessage().contains("xpath:")) {
                locator = "XPath extraido del log";
                locatorType = "xpath";
            } else if (error.getMessage().contains("UiSelector")) {
                locator = "UiSelector extraido del log";
                locatorType = "uiautomator";
            }
        }

        // Obtener estado de sesiones previas en serenity
        String ussdCode = (String) Serenity.sessionVariableCalled("currentUssdCode");

        return FailureContext.builder()
                .testName(testName)
                .stepDescription(stepDesc)
                .error(error)
                .stackTrace(stackTrace)
                .pageSource(pageSource)
                .elementLocator(locator)
                .elementType(locatorType)
                .ussdCode(ussdCode)
                .build();
    }

    private void analyzeWithOllama(FailureContext context) {

        String aiResponse = analyzer.analyze(context);

        String markdownReport =
                "#  Análisis Automatizado de Falla\n\n" +
                        "---\n\n" +
                        aiResponse + "\n";

        Serenity.recordReportData()
                .withTitle("Análisis IA Ollama")
                .andContents(markdownReport);
    }

    private String capturePageSourceWithRetry() {
        try {
            WebDriver baseDriver = Serenity.getWebdriverManager().getCurrentDriver();
            if (baseDriver == null)
                return null;

            WebDriver realDriver = baseDriver;
            if (baseDriver instanceof WebDriverFacade) {
                realDriver = ((WebDriverFacade) baseDriver).getProxiedDriver();
                if (realDriver == null)
                    return null;
            }

            long[] waitTimes = { 500, 1000, 1500 };

            for (long waitTime : waitTimes) {
                try {
                    String source = realDriver.getPageSource();
                    if (source != null && !source.trim().isEmpty()) {
                        return source;
                    }
                    Thread.sleep(waitTime);
                } catch (Exception ignored) {
                }
            }
        } catch (Exception ignored) {
        }

        return null; // fallback
    }

    private String extractStackTrace(Throwable throwable) {
        if (throwable == null)
            return "No stacktrace context available.";
        StringWriter sw = new StringWriter();
        throwable.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }


    @Override
    public void testSuiteStarted(Class<?> storyClass) {
    }

    @Override
    public void testSuiteStarted(Story story) {
    }

    @Override
    public void testSuiteFinished() {
    }

    @Override
    public void testStarted(String description) {
    }

    @Override
    public void testStarted(String description, String id) {
    }

    @Override
    public void testFinished(TestOutcome result) {
    }

    @Override
    public void testRetried() {
    }

    @Override
    public void stepStarted(ExecutedStepDescription description) {
    }

    @Override
    public void skippedStepStarted(ExecutedStepDescription description) {
    }

    @Override
    public void stepFinished() {
    }

    @Override
    public void stepIgnored() {
    }

    @Override
    public void stepPending() {
    }

    @Override
    public void stepPending(String message) {
    }

    @Override
    public void assumptionViolated(String message) {
    }

    @Override
    public void testRunFinished() {
    }


    public void testFinished(TestOutcome result, boolean isDataDriven, boolean stateExhausted) {
    }

    public void exampleFinished() {
    }

    public void exampleStarted(java.util.Map<String, String> data) {
    }

    @Override
    public void addNewExamplesFrom(net.thucydides.core.model.DataTable table) {
    }

    @Override
    public void useExamplesFrom(net.thucydides.core.model.DataTable table) {
    }

    @Override
    public void notifyScreenChange() {
    }

    @Override
    public void testIsManual() {
    }

    @Override
    public void testPending() {
    }

    @Override
    public void testSkipped() {
    }

    @Override
    public void testIgnored() {
    }

    @Override
    public void testFailed(TestOutcome result, Throwable cause) {
    }

    @Override
    public void lastStepFailed(StepFailure failure) {
    }
}
