package utils;

import hooks.ReportHooks;
import net.serenitybdd.core.Serenity;

public class EvidenciaUtils {

  private static int contadorPasos = 1;

  public static void registrarCaptura(String paso) {
    String pasoNumerado = contadorPasos++ + ". " + paso;

    ReportHooks.registrarPaso(paso);

    CapturaDePantallaMovil.tomarCapturaPantalla(pasoNumerado);

    // Integrar con Serenity
    Serenity.recordReportData().withTitle(paso).andContents(pasoNumerado);
    Serenity.takeScreenshot();
  }

  public static void reiniciarContador() {
    contadorPasos = 1;
  }
}