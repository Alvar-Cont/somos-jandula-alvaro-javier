package com.escaneoRedes.Escaneo.Redes.exceptions;

// Excepción personalizada para errores de la aplicación de redes
public class RedesException extends Exception {
    
    private int codigo;
    private String mensaje;
    
    // Constructor con código y mensaje
    public RedesException(int codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    // Constructor solo con mensaje
    public RedesException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }
    
    // Getters
    public int getCodigo() {
        return codigo;
    }
    
    public String getMensaje() {
        return mensaje;
    }
}
