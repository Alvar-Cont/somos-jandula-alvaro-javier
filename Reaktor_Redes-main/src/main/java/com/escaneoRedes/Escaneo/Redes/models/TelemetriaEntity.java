package com.escaneoRedes.Escaneo.Redes.models;

import com.escaneoRedes.Escaneo.Redes.Estado;
import jakarta.persistence.*;

// Entidad para almacenar el historial de estados de las redes
@Entity
@Table(name = "historial_estados")
public class TelemetriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la red que se conectó
    private String ssid;

    // Estado de la conexión (Conectado, FalloDeAuth, SinSenal)
    @Enumerated(EnumType.STRING)
    private Estado estado;

    // Fecha y hora del reporte
    @Column(name = "fecha_reporte")
    private String fechaReporte;

    // Constructor vacío requerido por JPA
    public TelemetriaEntity() {}

    // Constructor con parámetros
    public TelemetriaEntity(String ssid, String estado, String fechaReporte) {
        this.ssid = ssid;
        this.estado = Estado.valueOf(estado);
        this.fechaReporte = fechaReporte;
    }

    // Getters y Setters
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

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

}