package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.ConfigDTO;
import com.escaneoRedes.Escaneo.Redes.dto.CredencialesDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoVacioException;
import com.escaneoRedes.Escaneo.Redes.services.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador para agregar y consultar las redes
@RestController
public class EstadoController {

    @Autowired
    private CredencialService credencialService;

    // POST para agregar una nueva red
    @PostMapping("/configuracion-redes")
    public ResponseEntity<?> nuevaRed(@RequestBody CredencialesDTO cdto){
        try {
            credencialService.guardarNuevaRed(cdto);
            return ResponseEntity.ok().build();
        } catch (CampoObligatorioException e) {
            return ResponseEntity.status(400).body(e.getMensaje());
        } catch (CampoVacioException e) {
            return ResponseEntity.status(400).body(e.getMensaje());
        }
    }

    // GET para obtener todas las redes
    @GetMapping("/configuracion-redes")
    public ResponseEntity<?> listadoConfig(){
        List<ConfigDTO> confList = credencialService.obtenerTodasLasRedes();
        return ResponseEntity.ok().body(confList);
    }
}