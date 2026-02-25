package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.ConfigDTO;
import com.escaneoRedes.Escaneo.Redes.dto.CredencialesDTO;
import com.escaneoRedes.Escaneo.Redes.services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EstadoController {

    @Autowired
    private CredencialService credencialService;

    @PostMapping("/configuracion-redes")
    public ResponseEntity<?> nuevaRed(@RequestBody CredencialesDTO cdto){
        credencialService.guardarNuevaRed(cdto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/configuracion-redes")
    public ResponseEntity<?> listadoConfig(){
        List<ConfigDTO> confList = credencialService.obtenerTodasLasRedes();
        return ResponseEntity.ok().body(confList);
    }

    @DeleteMapping("/configuracion-redes/{id}")
    public ResponseEntity<?> borrarRed(@PathVariable Long id) {
        credencialService.borrarRedPorId(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint temporal para migrar passwords existentes a formato hasheado.
     * Solo ejecutar UNA VEZ para actualizar passwords legacy.
     * Después de ejecutarlo, puede eliminarse este endpoint.
     */
    @PostMapping("/migrar-passwords")
    public ResponseEntity<?> migrarPasswords() {
        int passwordsActualizadas = credencialService.migrarPasswordsAHash();
        return ResponseEntity.ok().body("Se hashearon " + passwordsActualizadas + " passwords");
    }
}