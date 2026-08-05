package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.MetricaDesempeno;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GestorMetricas {

    private static final List<MetricaDesempeno> metricas = new ArrayList<>();
    private static final String RUTA_ARCHIVO = "target/tiempos_respuesta.json";
    
    // Configurar el Hook para exportar cuando la JVM se apague
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(GestorMetricas::exportarAJSON));
    }

    /**
     * Registra una métrica directamente desde el listener automático de Serenity.
     * @param escenario Nombre del caso de prueba
     * @param flujo Nombre del paso o flujo
     * @param tiempoSegundos Duración exacta extraída de Serenity
     * @param estado Estado final (ej. "SUCCESS", "FAILURE")
     * @param dispositivo Nombre del dispositivo (ej. "Android")
     */
    public static synchronized void registrarMetricaAutomatica(String escenario, String flujo, double tiempoSegundos, String estado, String dispositivo) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        MetricaDesempeno metrica = new MetricaDesempeno(escenario, flujo, tiempoSegundos, estado, dispositivo, fecha);
        metricas.add(metrica);
    }

    /**
     * Exporta todas las métricas recolectadas a un archivo JSON.
     */
    private static void exportarAJSON() {
        if (metricas.isEmpty()) {
            return;
        }
        
        try {
            File file = new File(RUTA_ARCHIVO);
            file.getParentFile().mkdirs(); 
            
            try (java.io.Writer writer = new java.io.OutputStreamWriter(new java.io.FileOutputStream(file), java.nio.charset.StandardCharsets.UTF_8)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(metricas, writer);
                System.out.println("✅ Métricas Automáticas exportadas exitosamente en: " + RUTA_ARCHIVO);
            }
        } catch (IOException e) {
            System.err.println("❌ Error al exportar las métricas JSON: " + e.getMessage());
        }
    }
}
