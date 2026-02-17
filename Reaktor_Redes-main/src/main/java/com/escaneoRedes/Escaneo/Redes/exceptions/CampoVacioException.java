package com.escaneoRedes.Escaneo.Redes.exceptions;

// Excepción que se lanza cuando un campo obligatorio está vacío
public class CampoVacioException extends RedesException {
    
    // Constructor con el nombre del campo que está vacío
    public CampoVacioException(String nombreCampo) {
        super("El campo '" + nombreCampo + "' no puede estar vacío");
    }
    
    // Constructor con código de error y mensaje personalizado
    public CampoVacioException(int codigo, String mensaje) {
        super(codigo, mensaje);
    }
}
