package com.escaneoRedes.Escaneo.Redes.models;

import jakarta.persistence.*;

@Entity
@Table(name = "credenciales")
public class CredencialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ssid;
    private String contrasena;

    public CredencialEntity() {}

    public CredencialEntity(String ssid, String contrasena) {
        this.ssid = ssid;
        this.contrasena = contrasena;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}