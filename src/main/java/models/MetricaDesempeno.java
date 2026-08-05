package models;

public class MetricaDesempeno {
    private String escenario;
    private String flujo;
    private double tiempoSegundos;
    private String estado;
    private String dispositivo;
    private String fechaEjecucion;

    public MetricaDesempeno(String escenario, String flujo, double tiempoSegundos, String estado, String dispositivo, String fechaEjecucion) {
        this.escenario = escenario;
        this.flujo = flujo;
        this.tiempoSegundos = tiempoSegundos;
        this.estado = estado;
        this.dispositivo = dispositivo;
        this.fechaEjecucion = fechaEjecucion;
    }

    public String getEscenario() {
        return escenario;
    }

    public String getFlujo() {
        return flujo;
    }

    public double getTiempoSegundos() {
        return tiempoSegundos;
    }

    public String getEstado() {
        return estado;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public String getFechaEjecucion() {
        return fechaEjecucion;
    }
}
