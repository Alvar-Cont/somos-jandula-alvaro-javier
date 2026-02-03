package com.escaneoRedes.Escaneo.Redes.models;

import com.escaneoRedes.Escaneo.Redes.Estado;
import jakarta.persistence.*;

@Entity
@Table(name = "telemetria")
public class TelemetriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ssid;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    private String timestamp;

    public TelemetriaEntity() {}

    public TelemetriaEntity(String ssid, Estado estado, String timestamp) {
        this.ssid = ssid;
        this.estado = estado;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
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