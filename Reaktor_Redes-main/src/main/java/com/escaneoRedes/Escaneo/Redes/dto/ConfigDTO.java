package com.escaneoRedes.Escaneo.Redes.dto;

public class ConfigDTO {
    private String SSID;
    private String contrasena;

    public ConfigDTO() {
    }

    public ConfigDTO(String SSID, String contrasena) {
        this.SSID = SSID;
        this.contrasena = contrasena;
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
}
