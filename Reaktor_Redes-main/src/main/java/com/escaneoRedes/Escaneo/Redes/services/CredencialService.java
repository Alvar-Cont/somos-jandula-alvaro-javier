package com.escaneoRedes.Escaneo.Redes.services;

import com.escaneoRedes.Escaneo.Redes.dto.ConfigDTO;
import com.escaneoRedes.Escaneo.Redes.dto.CredencialesDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoVacioException;
import com.escaneoRedes.Escaneo.Redes.models.CredencialEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.CredencialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Service
public class CredencialService {
    
    @Autowired
    private CredencialRepository credencialRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
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
        
        // Hashear la password antes de guardar
        String passwordHasheada = passwordEncoder.encode(credencialDTO.getPassword());
        
        CredencialEntity entidad = new CredencialEntity(
            credencialDTO.getSsid(),
            credencialDTO.getUsuario(),
            passwordHasheada,
            credencialDTO.getSeguridad()
        );
        
        credencialRepository.save(entidad);
    }
    
    public List<ConfigDTO> obtenerTodasLasRedes() {
        List<CredencialEntity> redesGuardadas = credencialRepository.findAll();
        List<ConfigDTO> confList = new ArrayList<>();
        
        for (CredencialEntity c : redesGuardadas) {
            // No devolver la password hasheada por seguridad
            ConfigDTO dto = new ConfigDTO(c.getId(), c.getSsid(), c.getUsuario(), "********", c.getSeguridad());
            confList.add(dto);
        }
        
        return confList;
    }

    public void borrarRedPorId(Long id) {
        if (!credencialRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe una red con id " + id);
        }
        credencialRepository.deleteById(id);
    }

    /**
     * Método para migrar passwords existentes a formato hasheado.
     * Este método hashea todas las passwords que actualmente están en texto plano.
     * SOLO debe ejecutarse UNA VEZ para migrar datos legacy.
     */
    public int migrarPasswordsAHash() {
        List<CredencialEntity> todasLasRedes = credencialRepository.findAll();
        int actualizadas = 0;
        
        for (CredencialEntity red : todasLasRedes) {
            String passwordActual = red.getPassword();
            
            // Verificar si la password ya está hasheada (BCrypt hash empieza con $2a$, $2b$ o $2y$)
            if (passwordActual != null && !passwordActual.startsWith("$2")) {
                // Hashear la password y actualizar
                red.setPassword(passwordEncoder.encode(passwordActual));
                credencialRepository.save(red);
                actualizadas++;
            }
        }
        
        return actualizadas;
    }
}
