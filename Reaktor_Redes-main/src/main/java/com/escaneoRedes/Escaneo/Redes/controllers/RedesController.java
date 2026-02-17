package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.TelemetriaDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import com.escaneoRedes.Escaneo.Redes.services.TelemetriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador para el historial de estados (telemetría)
@RestController
public class RedesController {

    @Autowired
    private TelemetriaService telemetriaService;

    // POST para registrar un nuevo reporte
    @PostMapping("/registros-redes")
    public ResponseEntity<?> nuevaRed(@RequestBody TelemetriaDTO td) {
        try {
            telemetriaService.guardarTelemetria(td);
            return ResponseEntity.ok().build();
        } catch (CampoObligatorioException e) {
            return ResponseEntity.status(400).body(e.getMensaje());
        }
    }

    // GET para obtener el historial
    @GetMapping("/registros-redes")
    public ResponseEntity<?> listadoHistorico() {
        List<TelemetriaEntity> historico = telemetriaService.obtenerTodoElHistorial();
        return ResponseEntity.ok().body(historico);
    }
}