package com.escaneoRedes.Escaneo.Redes.models;

import jakarta.persistence.*;

// Entidad para almacenar las redes que hay que auditar
@Entity
@Table(name = "redes")
public class CredencialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre de la red wifi
    private String ssid;
    
    // Usuario para acceder a la red
    private String usuario;
    
    // Contraseña de la red
    private String password;
    
    // Tipo de seguridad (WPA2, WPA3, etc)
    private String seguridad;

    // Constructor vacío requerido por JPA
    public CredencialEntity() {}

    // Constructor con todos los parámetros
    public CredencialEntity(String ssid, String usuario, String password, String seguridad) {
        this.ssid = ssid;
        this.usuario = usuario;
        this.password = password;
        this.seguridad = seguridad;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }
}