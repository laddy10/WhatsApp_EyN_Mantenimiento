package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.EstadoPrueba;
import utils.WordAppium;

public class ReportHooks {

  private static final List<String> pasosEjecutados = new ArrayList<>();
  private static String lineaUsada = "Sin datos";
  private static String ultimoPaso = "";

  public static void registrarPaso(String paso) {
    pasosEjecutados.add(paso);
    ultimoPaso = paso;
  }

  public static void setLinea(String linea) {
    lineaUsada = linea;
  }

  @Before
  public void beforeEachScenario() {
    EstadoPrueba.inicio = System.currentTimeMillis();
    pasosEjecutados.clear();
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
  }

  @After
  public void generarReporteFinal(Scenario scenario) {
    EstadoPrueba.fin = System.currentTimeMillis();

    long duracionTotal = (EstadoPrueba.fin - EstadoPrueba.inicio) / 1000;
    long minutos = duracionTotal / 60;
    long segundos = duracionTotal % 60;
    String duracionFormato = minutos + " min " + segundos + " seg";

    String estadoFinal = EstadoPrueba.fallo ? "FAILED" : "PASSED";
    String pasoFallido = EstadoPrueba.fallo ? EstadoPrueba.pasoFallido : null;

    // ✅ Obtener el tag del escenario (ej: @WH_E&N_Soporte_Datos)
    String tag = "Sin tag";
    Collection<String> tags = scenario.getSourceTagNames();
    if (tags != null && !tags.isEmpty()) {
      tag = tags.iterator().next();
    }

    WordAppium.generarReporte(
            scenario.getName(),
            pasosEjecutados.toArray(new String[0]),
            lineaUsada,
            duracionFormato,
            pasoFallido,
            estadoFinal,
            tag);

    pasosEjecutados.clear();
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
  }
}