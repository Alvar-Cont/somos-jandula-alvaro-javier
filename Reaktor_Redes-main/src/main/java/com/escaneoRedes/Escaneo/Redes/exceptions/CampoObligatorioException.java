package com.escaneoRedes.Escaneo.Redes.exceptions;

// Excepción que se lanza cuando falta un campo obligatorio
public class CampoObligatorioException extends RedesException {
    
    // Constructor con el nombre del campo que falta
    public CampoObligatorioException(String nombreCampo) {
        super("El campo '" + nombreCampo + "' es obligatorio");
    }
    
    // Constructor con código de error y mensaje personalizado
    public CampoObligatorioException(int codigo, String mensaje) {
        super(codigo, mensaje);
    }
}
