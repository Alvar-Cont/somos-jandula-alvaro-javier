package com.escaneoRedes.Escaneo.Redes.services;

import com.escaneoRedes.Escaneo.Redes.dto.ConfigDTO;
import com.escaneoRedes.Escaneo.Redes.dto.CredencialesDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoVacioException;
import com.escaneoRedes.Escaneo.Redes.models.CredencialEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// Servicio para manejar la lógica de negocio de las credenciales de red
@Service
public class CredencialService {
    
    @Autowired
    private CredencialRepository credencialRepository;
    
    // Método para validar que los campos obligatorios no sean nulos
    private void validarCamposObligatorios(CredencialesDTO credencialDTO) throws CampoObligatorioException {
        if (credencialDTO.getSsid() == null) {
            throw new CampoObligatorioException("ssid");
        }
        if (credencialDTO.getUsuario() == null) {
            throw new CampoObligatorioException("usuario");
        }
        if (credencialDTO.getPassword() == null) {
            throw new CampoObligatorioException("password");
        }
    }
    
    // Método para validar que los campos no estén vacíos
    private void validarCamposNoVacios(CredencialesDTO credencialDTO) throws CampoVacioException {
        if (credencialDTO.getSsid().trim().isEmpty()) {
            throw new CampoVacioException("ssid");
        }
        if (credencialDTO.getUsuario().trim().isEmpty()) {
            throw new CampoVacioException("usuario");
        }
        if (credencialDTO.getPassword().trim().isEmpty()) {
            throw new CampoVacioException("password");
        }
    }
    
    // Método para guardar una nueva red
    public void guardarNuevaRed(CredencialesDTO credencialDTO) throws CampoObligatorioException, CampoVacioException {
        // Validar que los campos no sean nulos
        validarCamposObligatorios(credencialDTO);
        
        // Validar que los campos no estén vacíos
        validarCamposNoVacios(credencialDTO);
        
        // Crear la entidad y guardarla
        CredencialEntity entidad = new CredencialEntity(
            credencialDTO.getSsid(),
            credencialDTO.getUsuario(),
            credencialDTO.getPassword(),
            credencialDTO.getSeguridad()
        );
        
        credencialRepository.save(entidad);
    }
    
    // Método para obtener todas las redes guardadas
    public List<ConfigDTO> obtenerTodasLasRedes() {
        List<CredencialEntity> redesGuardadas = credencialRepository.findAll();
        List<ConfigDTO> confList = new ArrayList<>();
        
        // Convertir las entidades a DTOs
        for (CredencialEntity c : redesGuardadas) {
            ConfigDTO dto = new ConfigDTO(c.getSsid(), c.getUsuario(), c.getPassword(), c.getSeguridad());
            confList.add(dto);
        }
        
        return confList;
    }
}
