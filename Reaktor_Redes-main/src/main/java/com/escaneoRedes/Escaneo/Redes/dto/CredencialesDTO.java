package com.escaneoRedes.Escaneo.Redes.dto;

public class CredencialesDTO {
    private String SSID;
    private String contrasena;
    private String configSeguridad;

    public CredencialesDTO() {
    }

    public CredencialesDTO(String SSID, String contrasena, String configSeguridad) {
        this.SSID = SSID;
        this.contrasena = contrasena;
        this.configSeguridad = configSeguridad;
    }

    public String getSSID() {
        return SSID;
    }

    public void setSSID(String SSID) {
        this.SSID = SSID;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getConfigSeguridad() {
        return configSeguridad;
    }

    public void setConfigSeguridad(String configSeguridad) {
        configSeguridad = configSeguridad;
    }
}
