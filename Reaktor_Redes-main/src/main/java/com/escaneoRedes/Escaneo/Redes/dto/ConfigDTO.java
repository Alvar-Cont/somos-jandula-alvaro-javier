package com.escaneoRedes.Escaneo.Redes.dto;

public class ConfigDTO {
    
    private Long id;
    private String ssid;
    private String usuario;
    private String password;
    private String seguridad;

    // Constructor vacío
    public ConfigDTO() {
    }

    // Constructor con todos los parámetros
    public ConfigDTO(Long id, String ssid, String usuario, String password, String seguridad) {
        this.id = id;
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
