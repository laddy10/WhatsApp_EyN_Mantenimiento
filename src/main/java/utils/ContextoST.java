package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import cucumber.api.Scenario;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Deja constancia, escenario por escenario, de CON QUE LINEA se corrio la prueba. Es el lado
 * "proyecto" del contrato st-context con Smart Tester.
 *
 * <p>Como funciona: al terminar cada escenario se escribe UN archivo JSON en {@code
 * target/st-context/}. El orquestador lee esa carpeta antes de archivar el workspace, la mete en
 * report_metadata.json y la publica en /api/status; Smart Tester la convierte en la variable de
 * plantilla {@code {{lineaPrueba}}}.
 *
 * <p>Aqui la linea son los ULTIMOS 4 DIGITOS: es lo unico que existe: real-user.json guarda solo
 * eso y el bot de Claro muestra los numeros enmascarados (****9612). Se registra en dos momentos:
 * cuando el escenario PIDE una linea y cuando esa linea aparece en el menu del bot y se elige su
 * opcion ({@code confirmada}). Asi, un escenario que falla ANTES de elegir igual informa que linea
 * iba a usar, sin afirmar que llego a usarla.
 *
 * <p>Un archivo por escenario (y no uno compartido) a proposito: el build corre con {@code
 * maxParallelForks}, y varios JVM escribiendo el mismo archivo se pisarian. Ademas, si la corrida
 * se cae a la mitad, lo ya escrito sobrevive.
 *
 * <p>REGLA: aqui no se escriben credenciales. Este archivo viaja a Smart Tester y su contenido
 * termina en mensajes de WhatsApp/Teams.
 */
public class ContextoST {

  private static final String CARPETA = "target/st-context";

  /** Lineas elegidas en el escenario en curso, en orden. Se limpia en cada @Before. */
  private static final List<Map<String, Object>> SELECCIONES = new ArrayList<>();

  /** Cuenta elegida cuando el flujo NO va por numero de linea (Hogar: una direccion). */
  private static String cuenta = null;

  private ContextoST() {}

  /** Arranque de escenario: olvidar las lineas del anterior (los estaticos sobreviven la corrida). */
  public static synchronized void reiniciar() {
    SELECCIONES.clear();
    cuenta = null;
  }

  /**
   * El escenario elige una CUENTA, no una linea. Es el caso de Hogar: el menu del bot lista
   * direcciones, no numeros, asi que no hay linea que informar. Va a {@code {{cuentaPrueba}}}.
   */
  public static synchronized void registrarCuenta(String descripcion) {
    cuenta = descripcion;
  }

  /**
   * El escenario PIDE una linea. Todavia no se sabe si existe en el menu del bot: eso lo marca
   * {@link #confirmarSeleccion}.
   *
   * @param numeroConfigurado el valor tal como esta en real-user.json. Si trae el numero COMPLETO
   *     ("310 263 9612") se informa completo; si trae solo los ultimos 4 ("9612"), enmascarado
   *     ("****9612"). En ambos casos la seleccion en el menu del bot sigue funcionando igual,
   *     porque se hace por los ultimos 4 digitos.
   */
  public static synchronized void registrarLinea(String numeroConfigurado, String origen) {
    try {
      Map<String, Object> seleccion = new LinkedHashMap<>();
      seleccion.put("linea", formatearLinea(numeroConfigurado));
      seleccion.put("ultimos4", ultimos4De(numeroConfigurado));
      seleccion.put("opcionMenu", null);
      seleccion.put("confirmada", false);
      seleccion.put("origen", origen);
      SELECCIONES.add(seleccion);
    } catch (Exception e) {
      System.err.println("[ContextoST] No se pudo registrar la linea: " + e);
    }
  }

  /** La linea aparecio en el menu del bot y se eligio esa opcion: dato confirmado en pantalla. */
  public static synchronized void confirmarSeleccion(String numeroConfigurado, int opcionMenu) {
    try {
      String ultimos4 = ultimos4De(numeroConfigurado);
      for (int i = SELECCIONES.size() - 1; i >= 0; i--) {
        Map<String, Object> seleccion = SELECCIONES.get(i);
        if (ultimos4.equals(seleccion.get("ultimos4"))) {
          seleccion.put("opcionMenu", String.valueOf(opcionMenu));
          seleccion.put("confirmada", true);
          return;
        }
      }
      // Nadie la registro antes (uso directo): se agrega ya confirmada.
      Map<String, Object> seleccion = new LinkedHashMap<>();
      seleccion.put("linea", formatearLinea(numeroConfigurado));
      seleccion.put("ultimos4", ultimos4);
      seleccion.put("opcionMenu", String.valueOf(opcionMenu));
      seleccion.put("confirmada", true);
      seleccion.put("origen", "menu");
      SELECCIONES.add(seleccion);
    } catch (Exception e) {
      System.err.println("[ContextoST] No se pudo confirmar la linea: " + e);
    }
  }

  /** Ultimos 4 digitos de un valor, venga completo o ya recortado. */
  public static String ultimos4De(String valor) {
    String digitos = valor == null ? "" : valor.replaceAll("\\D", "");
    return digitos.length() <= 4 ? digitos : digitos.substring(digitos.length() - 4);
  }

  /** Numero completo si real-user.json lo tiene; si no, los 4 digitos enmascarados. */
  private static String formatearLinea(String numeroConfigurado) {
    String valor = numeroConfigurado == null ? "" : numeroConfigurado.trim();
    String digitos = valor.replaceAll("\\D", "");
    if (digitos.isEmpty()) {
      return "";
    }
    return digitos.length() > 4 ? valor : "****" + digitos;
  }

  /**
   * Escribe el contexto del escenario que acaba de terminar. Nunca lanza: si algo falla, el
   * escenario no se entera (esto es telemetria, no parte de la prueba).
   */
  public static synchronized void registrarEscenario(Scenario scenario) {
    try {
      List<String> tags = new ArrayList<>();
      if (scenario != null && scenario.getSourceTagNames() != null) {
        tags.addAll(scenario.getSourceTagNames());
      }

      Map<String, Object> datos = new LinkedHashMap<>();
      datos.put("escenario", scenario == null ? null : scenario.getName());
      datos.put("tags", tags);
      // 'linea' y 'cuenta' son los campos estandar del contrato (-> {{lineaPrueba}} y
      // {{cuentaPrueba}} en las plantillas). Si el escenario cambio de linea a mitad
      // de camino van todas, en orden.
      datos.put("linea", lineaResumen());
      datos.put("lineaCuenta", lineaCuentaWhatsApp());
      datos.put("cuenta", cuenta);
      datos.put("selecciones", new ArrayList<>(SELECCIONES));
      datos.put("resultado", scenario != null && scenario.isFailed() ? "FAILED" : "PASSED");
      datos.put("registradoEn", LocalDateTime.now().toString());

      escribir(datos);

      System.out.println(
          "[ContextoST] Escenario registrado | linea=" + datos.get("linea")
              + (cuenta == null ? "" : " | cuenta=" + cuenta));

    } catch (Exception e) {
      System.err.println("[ContextoST] No se pudo registrar el contexto del escenario: " + e);
    }
  }

  /**
   * Linea de la cuenta de WhatsApp desde la que se prueba: el chip del celular que esta
   * chateando con Claro. NO es la linea que el escenario consulta en el menu del bot — son
   * dos cosas distintas y la prueba nunca menciona esta, porque no le hace falta.
   *
   * <p>Es fija por celular, asi que la inyecta el orquestador como {@code -Dwhatsapp.linea}
   * a partir del campo {@code whatsapp_linea} de devices.json. Cadena vacia si ese celular
   * todavia no la tiene configurada.
   */
  public static String lineaCuentaWhatsApp() {
    String linea = System.getProperty("whatsapp.linea", "");
    return linea == null ? "" : linea.trim();
  }

  /**
   * Lo que va en el campo "LINEA Y PLAN" del informe Word: las dos lineas, etiquetadas, para
   * que no se confundan. Si falta alguna, sale solo la que hay.
   */
  public static synchronized String lineaParaInforme() {
    String consultada = identificacionUsada();
    String cuenta = lineaCuentaWhatsApp();
    if (cuenta.isEmpty()) {
      return consultada;
    }
    if (consultada.isEmpty()) {
      return "Cuenta WhatsApp: " + cuenta;
    }
    return "Consultada: " + consultada + " | Cuenta WhatsApp: " + cuenta;
  }

  /**
   * Lo que identifica esta prueba: la linea usada o, si el flujo no va por linea (Hogar), la
   * cuenta. Cadena vacia si el escenario fallo antes de elegir nada.
   *
   * <p>Lo usa tambien el informe Word: este es el registro que se llena donde la linea se elige
   * DE VERDAD, asi que no arrastra el valor del escenario anterior.
   */
  public static synchronized String identificacionUsada() {
    String linea = lineaResumen();
    if (!linea.isEmpty()) {
      return linea;
    }
    return cuenta == null ? "" : cuenta;
  }

  /** La linea usada, o varias unidas por " -> " si el escenario cambio. Vacio si no eligio ninguna. */
  private static String lineaResumen() {
    StringBuilder sb = new StringBuilder();
    for (Map<String, Object> seleccion : SELECCIONES) {
      Object linea = seleccion.get("linea");
      if (linea == null || String.valueOf(linea).isEmpty()) {
        continue;
      }
      if (sb.length() > 0) {
        sb.append(" -> ");
      }
      sb.append(linea);
    }
    return sb.toString();
  }

  private static void escribir(Map<String, Object> datos) throws Exception {
    Path carpeta = Paths.get(CARPETA);
    Files.createDirectories(carpeta);
    String nombre =
        System.currentTimeMillis() + "-" + UUID.randomUUID().toString().substring(0, 8) + ".json";
    File destino = carpeta.resolve(nombre).toFile();
    new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(destino, datos);
  }
}
