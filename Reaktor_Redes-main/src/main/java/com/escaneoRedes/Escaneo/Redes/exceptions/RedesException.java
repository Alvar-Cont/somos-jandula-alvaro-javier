package com.escaneoRedes.Escaneo.Redes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RedesException extends ResponseStatusException {
    
    public RedesException(HttpStatus status, String mensaje) {
        super(status, mensaje);
    }
}
