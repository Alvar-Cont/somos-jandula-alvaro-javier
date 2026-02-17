package com.escaneoRedes.Escaneo.Redes.dto;

public class TelemetriaDTO {
    
    private String ssid;
    private String estado;
    private String fechaReporte;

    // Constructor vacío
    public TelemetriaDTO() {
    }

    // Constructor con parámetros
    public TelemetriaDTO(String ssid, String estado, String fechaReporte) {
        this.ssid = ssid;
        this.estado = estado;
        this.fechaReporte = fechaReporte;
    }

    // Getters y Setters
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
}
