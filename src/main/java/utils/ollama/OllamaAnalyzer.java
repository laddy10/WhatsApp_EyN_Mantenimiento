package utils.ollama;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;

public class OllamaAnalyzer {

  private final OllamaClient client;
  private static final int MAX_PAGE_SOURCE_LENGTH = 3000;
  private static final int MAX_LINE_LENGTH = 120;

  public enum AnalysisType {
    ELEMENT_NOT_FOUND,
    TIMEOUT,
    STALE_ELEMENT,
    GENERAL
  }

  public OllamaAnalyzer() {
    this.client = new OllamaClient();
  }

  public String analyze(FailureContext context) {
    if (!client.isEnabled()) {
      return "Ollama is disabled.";
    }

    AnalysisType type = determineAnalysisType(context.getError());
    String prompt;

    switch (type) {
      case ELEMENT_NOT_FOUND:
        prompt = buildElementNotFoundPrompt(context);
        break;
      default:
        prompt = buildGeneralAnalysisPrompt(context);
        break;
    }

    return client.ask(prompt);
  }

  private AnalysisType determineAnalysisType(Throwable error) {
    if (error == null) return AnalysisType.GENERAL;

    if (error instanceof NoSuchElementException ||
            (error.getMessage() != null && error.getMessage().contains("NoSuchElementException"))) {
      return AnalysisType.ELEMENT_NOT_FOUND;
    }

    if (error instanceof TimeoutException ||
            (error.getMessage() != null && error.getMessage().contains("TimeoutException"))) {
      return AnalysisType.TIMEOUT;
    }

    if (error instanceof StaleElementReferenceException ||
            (error.getMessage() != null && error.getMessage().contains("StaleElementReferenceException"))) {
      return AnalysisType.STALE_ELEMENT;
    }

    return AnalysisType.GENERAL;
  }

  private String buildElementNotFoundPrompt(FailureContext context) {

    StringBuilder prompt = new StringBuilder();

    prompt.append("Actúa como un QA Automation Senior experto en Appium y Serenity BDD.\n");
    prompt.append("Analiza el siguiente fallo de tipo ELEMENT_NOT_FOUND.\n\n");

    prompt.append("Responde EXCLUSIVAMENTE en formato Markdown profesional.\n");
    prompt.append("No uses bloques de código.\n");
    prompt.append("No uses HTML.\n");
    prompt.append("No incluyas líneas extremadamente largas.\n\n");

    prompt.append("Estructura obligatoria:\n");
    prompt.append("##  Resumen Ejecutivo\n");
    prompt.append("##  Causa Raíz Probable\n");
    prompt.append("##  Alternativas Detectadas\n");
    prompt.append("##  Solución Recomendada\n");
    prompt.append("##  Acción Preventiva\n\n");

    prompt.append("- Paso: ").append(context.getStepDescription()).append("\n");
    prompt.append("- Localizador buscado: ")
            .append(context.getElementLocator())
            .append(" (Tipo: ")
            .append(context.getElementType())
            .append(")\n");

    prompt.append("- Código USSD Activo: ")
            .append(context.getUssdCode() != null ? context.getUssdCode() : "N/A")
            .append("\n\n");

    if (context.isPageSourceValid()) {
      String pageSource = context.getPageSource();

      if (pageSource.length() > MAX_PAGE_SOURCE_LENGTH) {
        pageSource = pageSource.substring(0, MAX_PAGE_SOURCE_LENGTH) + "... [TRUNCATED]";
      }

      prompt.append("Fragmento relevante del Page Source:\n");
      prompt.append(wrapLongLines(pageSource)).append("\n\n");
    } else {
      prompt.append("El Page Source no está disponible.\n\n");
    }

    return prompt.toString();
  }

  private String buildGeneralAnalysisPrompt(FailureContext context) {

    StringBuilder prompt = new StringBuilder();

    prompt.append("Actúa como un QA Automation Senior experto en Serenity BDD.\n");
    prompt.append("Analiza el siguiente fallo técnico.\n\n");

    prompt.append("Responde EXCLUSIVAMENTE en formato Markdown profesional.\n");
    prompt.append("No uses bloques de código.\n");
    prompt.append("No incluyas el stacktrace completo.\n");
    prompt.append("No uses HTML.\n\n");

    prompt.append("Estructura obligatoria:\n");
    prompt.append("##  Resumen Ejecutivo\n");
    prompt.append("##  Causa Raíz\n");
    prompt.append("##  Impacto\n");
    prompt.append("##  Solución Recomendada\n");
    prompt.append("##  Acción Preventiva\n\n");

    prompt.append("- Test: ").append(context.getTestName()).append("\n");
    prompt.append("- Paso: ").append(context.getStepDescription()).append("\n");

    Throwable error = context.getError();
    if (error != null && error.getMessage() != null) {
      prompt.append("- Mensaje principal del error: ")
              .append(wrapLongLines(error.getMessage()))
              .append("\n");
    }

    return prompt.toString();
  }

  private String wrapLongLines(String text) {
    if (text == null) return "";

    StringBuilder wrapped = new StringBuilder();
    int count = 0;

    for (char c : text.toCharArray()) {
      wrapped.append(c);
      count++;

      if (count >= MAX_LINE_LENGTH && c == ' ') {
        wrapped.append("\n");
        count = 0;
      }
    }

    return wrapped.toString();
  }
}