package hooks;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.CausaFallo;
import utils.ContextoST;
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

  /**
   * @deprecated la línea del informe sale de {@link ContextoST}, que la registra donde de verdad
   *     se elige (SeleccionarNumero). Este registro se llenaba desde los steps y quedaba
   *     desfasado. Se mantiene para no tocar los steps que aún lo invocan.
   */
  @Deprecated
  public static void setLinea(String linea) {
    lineaUsada = linea;
  }

  @Before
  public void beforeEachScenario() {
    EstadoPrueba.inicio = System.currentTimeMillis();
    pasosEjecutados.clear();
    ultimoPaso = "";
    lineaUsada = "Sin datos";
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
    // Todo lo de arriba es estático y sobrevive toda la corrida: sin limpiarlo, el
    // escenario hereda los datos del anterior.
    ContextoST.reiniciar();
  }

  // order bajo = este @After se ejecuta el ÚLTIMO (Cucumber corre los @After en orden
  // descendente). Hace falta para que ErrorScreenshotHooks (order 10) ya haya guardado
  // Error/error.png cuando el informe la va a insertar.
  @After(order = 1)
  public void generarReporteFinal(Scenario scenario) {
    // Contrato st-context: con qué línea corrió el escenario, para las alertas de
    // Smart Tester ({{lineaPrueba}}). Va primero para que un fallo del informe Word
    // no se lleve por delante el dato.
    ContextoST.registrarEscenario(scenario);

    EstadoPrueba.fin = System.currentTimeMillis();

    long duracionTotal = (EstadoPrueba.fin - EstadoPrueba.inicio) / 1000;
    long minutos = duracionTotal / 60;
    long segundos = duracionTotal % 60;
    String duracionFormato = minutos + " min " + segundos + " seg";

    // El estado sale del propio Cucumber. Antes salía de EstadoPrueba.fallo, que NADIE
    // ponía en true: el informe daba por buena cualquier prueba, incluso las que se caían.
    boolean fallo = scenario.isFailed();
    EstadoPrueba.fallo = fallo;
    String estadoFinal = fallo ? "FAILED" : "PASSED";
    String pasoFallido =
        fallo ? (pasosEjecutados.isEmpty() ? "Paso no identificado" : ultimoPaso) : null;
    EstadoPrueba.pasoFallido = pasoFallido;
    String motivoFallo = fallo ? CausaFallo.descripcionCorta() : "";

    // La línea sale del registro que se llena donde se elige, no del estático lineaUsada.
    String identificacion = ContextoST.identificacionUsada();

    // ✅ Obtener el tag del escenario (ej: @WH_E&N_Soporte_Datos)
    String tag = "Sin tag";
    Collection<String> tags = scenario.getSourceTagNames();
    if (tags != null && !tags.isEmpty()) {
      tag = tags.iterator().next();
    }

    WordAppium.generarReporte(
            scenario.getName(),
            pasosEjecutados.toArray(new String[0]),
            identificacion.isEmpty() ? "Sin datos" : identificacion,
            duracionFormato,
            pasoFallido,
            estadoFinal,
            tag,
            motivoFallo);

    pasosEjecutados.clear();
    ultimoPaso = "";
    lineaUsada = "Sin datos";
    EstadoPrueba.fallo = false;
    EstadoPrueba.pasoFallido = "";
  }
}