package utils;

import net.thucydides.core.model.stacktrace.FailureCause;
import net.thucydides.core.steps.StepEventBus;

/**
 * Por que fallo el escenario, en una linea legible para el informe Word.
 *
 * <p>El dato sale de Serenity, que ya guarda la excepcion del escenario en curso ({@code
 * StepEventBus -> BaseStepListener -> FailureCause}). Cucumber no se la pasa a los hooks, asi que
 * preguntarle a Serenity es la unica via limpia.
 *
 * <p>El mensaje se LIMPIA a proposito: Selenium y Appium cuelgan del texto del error bloques de
 * diagnostico ("Build info:", "System info:", capabilities, sesion) y a veces la traza entera. En
 * el informe eso no aporta y tapa lo unico que importa, que es la frase del error. Aqui se corta
 * todo eso y se recorta a {@value #MAX_CARACTERES} caracteres.
 */
public class CausaFallo {

  private static final int MAX_CARACTERES = 500;

  /** Donde empieza el ruido de diagnostico de Selenium/Appium dentro del mensaje. */
  private static final String[] CORTES = {
    "Build info:",
    "System info:",
    "Driver info:",
    "Command duration or timeout",
    "For documentation on this error",
    "*** Element info:",
    "Capabilities {",
    "Session ID:"
  };

  private CausaFallo() {}

  /**
   * Algo como "NoSuchElementException: Unable to locate element: ...". Cadena vacia si no se pudo
   * averiguar (nunca lanza: el informe debe generarse igual).
   */
  public static String descripcionCorta() {
    try {
      StepEventBus bus = StepEventBus.getEventBus();
      if (bus == null || !bus.isBaseStepListenerRegistered()) {
        return "";
      }
      FailureCause causa = bus.getBaseStepListener().getTestFailureCause();
      if (causa == null) {
        return "";
      }
      String tipo = causa.getSimpleErrorType() == null ? "" : causa.getSimpleErrorType().trim();
      String mensaje = limpiar(causa.getMessage());
      if (mensaje.isEmpty()) {
        return tipo;
      }
      return tipo.isEmpty() ? mensaje : tipo + ": " + mensaje;
    } catch (Exception | LinkageError e) {
      System.err.println("[CausaFallo] No se pudo obtener la causa del fallo: " + e);
      return "";
    }
  }

  /** Quita los bloques de diagnostico, las lineas de traza y recorta. */
  private static String limpiar(String mensaje) {
    if (mensaje == null) {
      return "";
    }
    String texto = mensaje;
    for (String corte : CORTES) {
      int i = texto.indexOf(corte);
      if (i > 0) {
        texto = texto.substring(0, i);
      }
    }

    StringBuilder sb = new StringBuilder();
    for (String linea : texto.split("\\R")) {
      String l = linea.trim();
      if (l.isEmpty() || l.startsWith("at ")) {
        continue;
      }
      if (sb.length() > 0) {
        sb.append(" ");
      }
      sb.append(l);
    }

    String limpio = sb.toString().trim();
    if (limpio.length() > MAX_CARACTERES) {
      limpio = limpio.substring(0, MAX_CARACTERES).trim() + "...";
    }
    return limpio;
  }
}
