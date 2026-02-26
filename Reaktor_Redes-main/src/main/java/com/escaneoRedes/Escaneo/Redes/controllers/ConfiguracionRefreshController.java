package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.ConfiguracionRefreshDTO;
import com.escaneoRedes.Escaneo.Redes.services.ConfiguracionRefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfiguracionRefreshController {

    @Autowired
    private ConfiguracionRefreshService configuracionRefreshService;

    @GetMapping("/configuracion-refresh-redes")
    public ResponseEntity<ConfiguracionRefreshDTO> obtenerConfiguracion() {
        ConfiguracionRefreshDTO configuracion = configuracionRefreshService.obtenerConfiguracionGlobal();
        return ResponseEntity.ok(configuracion);
    }

    @PutMapping("/configuracion-refresh-redes")
    public ResponseEntity<ConfiguracionRefreshDTO> guardarConfiguracion(
            @RequestBody ConfiguracionRefreshDTO configuracionRefreshDTO) {

        ConfiguracionRefreshDTO configuracionGuardada = configuracionRefreshService
                .guardarConfiguracionGlobal(configuracionRefreshDTO);

        return ResponseEntity.ok(configuracionGuardada);
    }
}
