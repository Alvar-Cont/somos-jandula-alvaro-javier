package com.escaneoRedes.Escaneo.Redes.controllers;

import com.escaneoRedes.Escaneo.Redes.dto.ConfigDTO;
import com.escaneoRedes.Escaneo.Redes.dto.CredencialesDTO;
import com.escaneoRedes.Escaneo.Redes.models.CredencialEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EstadoController {

    @Autowired
    private CredencialRepository credencialRepository;

    @PostMapping("/configuracion-redes")
    public ResponseEntity<?> nuevaRed(@RequestBody CredencialesDTO cdto){
        if (cdto.getSSID() == null || cdto.getContrasena() == null){
            return ResponseEntity.status(404).body("Valor no añadido");
        }
        if (cdto.getSSID().equalsIgnoreCase("") || cdto.getContrasena().equalsIgnoreCase("")){
            return ResponseEntity.status(404).body("Valor vacio");
        }

        CredencialEntity entidad = new CredencialEntity(cdto.getSSID(), cdto.getContrasena());
        credencialRepository.save(entidad);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/configuracion-redes")
    public ResponseEntity<?> listadoConfig(){
        List<CredencialEntity> redesGuardadas = credencialRepository.findAll();
        List<ConfigDTO> confList = new ArrayList<>();

        for (CredencialEntity c : redesGuardadas){
            ConfigDTO dto = new ConfigDTO(c.getSsid(), c.getContrasena());
            confList.add(dto);
        }
        return ResponseEntity.ok().body(confList);
    }
}