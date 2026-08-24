package utils;

import models.EstadoAsesor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/** Estado compartido y persistente para no iniciar nuevos saludos si hay asesor pendiente. */
public final class EstadoAtencionHumana {

  private static final Path ARCHIVO_ESTADO = Paths.get(
      System.getProperty("whatsapp.asesor.estado.path", ".runtime/asesor-pendiente.flag"));

  private EstadoAtencionHumana() {}

  public static EstadoAsesor leerEstado() {
    if (!Files.exists(ARCHIVO_ESTADO)) {
      return EstadoAsesor.SIN_CONVERSACION_PENDIENTE;
    }
    try {
      List<String> lines = Files.readAllLines(ARCHIVO_ESTADO, StandardCharsets.UTF_8);
      if (!lines.isEmpty()) {
        String estadoStr = lines.get(0).trim();
        
        // Validar expiración de 30 minutos
        if (lines.size() > 1 && lines.get(1).startsWith("timestamp=")) {
          try {
            long timestamp = Long.parseLong(lines.get(1).substring("timestamp=".length()));
            long diferenciaMinutos = (System.currentTimeMillis() - timestamp) / (60 * 1000);
            
            if (diferenciaMinutos >= 30) {
              System.err.println("El estado " + estadoStr + " expiró tras " + diferenciaMinutos + " minutos. Limpiando marca persistente.");
              eliminarMarca();
              return EstadoAsesor.SIN_CONVERSACION_PENDIENTE;
            }
          } catch (NumberFormatException e) {
            System.err.println("Error parseando timestamp: " + e.getMessage());
          }
        }

        try {
          return EstadoAsesor.valueOf(estadoStr);
        } catch (IllegalArgumentException e) {
          return EstadoAsesor.SIN_CONVERSACION_PENDIENTE;
        }
      }
    } catch (IOException e) {
      System.err.println("No se pudo leer el estado: " + e.getMessage());
    }
    return EstadoAsesor.SIN_CONVERSACION_PENDIENTE;
  }

  public static void guardarEstado(EstadoAsesor estado) {
    if (estado == EstadoAsesor.SIN_CONVERSACION_PENDIENTE || estado == EstadoAsesor.CERRADO) {
      eliminarMarca();
      return;
    }
    escribirMarca(estado.name());
  }

  public static boolean requiereRecuperacion() {
    return leerEstado() != EstadoAsesor.SIN_CONVERSACION_PENDIENTE;
  }

  public static void marcarEnCola() {
    guardarEstado(EstadoAsesor.EN_COLA);
  }
  
  public static void marcarAsesorActivo() {
    guardarEstado(EstadoAsesor.ASESOR_ACTIVO);
  }

  public static void marcarCierrePendiente() {
    guardarEstado(EstadoAsesor.CIERRE_PENDIENTE);
  }

  public static void marcarCerrado() {
    guardarEstado(EstadoAsesor.CERRADO);
  }

  public static String rutaMarcaPersistente() {
    return ARCHIVO_ESTADO.toAbsolutePath().toString();
  }

  private static void escribirMarca(String estado) {
    try {
      Path parent = ARCHIVO_ESTADO.toAbsolutePath().getParent();
      if (parent != null) {
        Files.createDirectories(parent);
      }
      Files.write(
          ARCHIVO_ESTADO,
          (estado + System.lineSeparator() + "timestamp=" + System.currentTimeMillis())
              .getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
      throw new IllegalStateException(
          "No se pudo persistir el estado de atencion humana en "
              + ARCHIVO_ESTADO.toAbsolutePath(), e);
    }
  }

  private static void eliminarMarca() {
    try {
      Files.deleteIfExists(ARCHIVO_ESTADO);
    } catch (IOException e) {
      throw new IllegalStateException(
          "No se pudo limpiar el estado de atencion humana en "
              + ARCHIVO_ESTADO.toAbsolutePath(), e);
    }
  }
}
