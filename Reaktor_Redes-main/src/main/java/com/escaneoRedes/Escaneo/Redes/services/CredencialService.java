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

@Service
public class CredencialService {
    
    @Autowired
    private CredencialRepository credencialRepository;
    
    private void validarCamposObligatorios(CredencialesDTO credencialDTO) {
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
    
    private void validarCamposNoVacios(CredencialesDTO credencialDTO) {
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
    
    public void guardarNuevaRed(CredencialesDTO credencialDTO) {
        validarCamposObligatorios(credencialDTO);
        validarCamposNoVacios(credencialDTO);
        
        CredencialEntity entidad = new CredencialEntity(
            credencialDTO.getSsid(),
            credencialDTO.getUsuario(),
            credencialDTO.getPassword(),
            credencialDTO.getSeguridad()
        );
        
        credencialRepository.save(entidad);
    }
    
    public List<ConfigDTO> obtenerTodasLasRedes() {
        List<CredencialEntity> redesGuardadas = credencialRepository.findAll();
        List<ConfigDTO> confList = new ArrayList<>();
        
        for (CredencialEntity c : redesGuardadas) {
            ConfigDTO dto = new ConfigDTO(c.getSsid(), c.getUsuario(), c.getPassword(), c.getSeguridad());
            confList.add(dto);
        }
        
        return confList;
    }
}
