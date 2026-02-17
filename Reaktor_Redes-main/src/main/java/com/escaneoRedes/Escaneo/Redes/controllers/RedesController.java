package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.TelemetriaDTO;
import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import com.escaneoRedes.Escaneo.Redes.services.TelemetriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedesController {

    @Autowired
    private TelemetriaService telemetriaService;

    @PostMapping("/registros-redes")
    public ResponseEntity<?> nuevaRed(@RequestBody TelemetriaDTO td) {
        telemetriaService.guardarTelemetria(td);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/registros-redes")
    public ResponseEntity<?> listadoHistorico() {
        List<TelemetriaEntity> historico = telemetriaService.obtenerTodoElHistorial();
        return ResponseEntity.ok().body(historico);
    }
}