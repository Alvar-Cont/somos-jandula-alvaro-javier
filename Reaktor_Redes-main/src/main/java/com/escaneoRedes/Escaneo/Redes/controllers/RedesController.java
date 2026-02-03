package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.TelemetriaDTO;
import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.TelemetriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RedesController {

    @Autowired
    private TelemetriaRepository telemetriaRepository;

    @PostMapping("/RegistroTelemetria")
    public ResponseEntity<?> nuevaRed(@RequestBody TelemetriaDTO td) {
        if (td.getSSID() == null || td.getEstado() == null || td.getTimestamp() == null) {
            return ResponseEntity.status(400).body("Valor no añadido");
        }
        if (td.getSSID().equalsIgnoreCase("")) {
            return ResponseEntity.status(400).body("Valor vacío");
        }

        TelemetriaEntity entidad = new TelemetriaEntity(td.getSSID(), td.getEstado(), td.getTimestamp());
        telemetriaRepository.save(entidad);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/consultaHistorico")
    public ResponseEntity<?> listadoHistorico() {
        List<TelemetriaEntity> historico = telemetriaRepository.findAll();
        return ResponseEntity.ok().body(historico);
    }
}