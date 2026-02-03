package com.escaneoRedes.Escaneo.Redes.dto;

import com.escaneoRedes.Escaneo.Redes.Estado;
import java.sql.Timestamp;

public class TelemetriaDTO {
    private String SSID;
    private Estado estado;
    private String timestamp;

    public TelemetriaDTO() {
    }

    public TelemetriaDTO(String SSID, Estado estado, String timestamp) {
        this.SSID = SSID;
        this.estado = estado;
        this.timestamp = timestamp;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
