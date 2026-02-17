package com.escaneoRedes.Escaneo.Redes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CampoObligatorioException extends ResponseStatusException {
    
    public CampoObligatorioException(String nombreCampo) {
        super(HttpStatus.BAD_REQUEST, "El campo '" + nombreCampo + "' es obligatorio");
    }
}
