package com.escaneoRedes.Escaneo.Redes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CampoVacioException extends ResponseStatusException {
    
    public CampoVacioException(String nombreCampo) {
        super(HttpStatus.BAD_REQUEST, "El campo '" + nombreCampo + "' no puede estar vacío");
    }
}
