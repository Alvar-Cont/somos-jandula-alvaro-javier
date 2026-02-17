package com.escaneoRedes.Escaneo.Redes.services;

import com.escaneoRedes.Escaneo.Redes.dto.TelemetriaDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.TelemetriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Servicio para manejar la lógica de negocio de la telemetría
@Service
public class TelemetriaService {
    
    @Autowired
    private TelemetriaRepository telemetriaRepository;
    
    // Método para validar que los campos obligatorios no sean nulos
    private void validarCamposObligatorios(TelemetriaDTO telemetriaDTO) throws CampoObligatorioException {
        if (telemetriaDTO.getSsid() == null) {
            throw new CampoObligatorioException("ssid");
        }
        if (telemetriaDTO.getEstado() == null) {
            throw new CampoObligatorioException("estado");
        }
        if (telemetriaDTO.getFechaReporte() == null) {
            throw new CampoObligatorioException("fechaReporte");
        }
    }
    
    // Método para guardar un nuevo registro de telemetría
    public void guardarTelemetria(TelemetriaDTO telemetriaDTO) throws CampoObligatorioException {
        // Validar que los campos principales no sean nulos
        validarCamposObligatorios(telemetriaDTO);
        
        // Crear la entidad y guardarla
        TelemetriaEntity entidad = new TelemetriaEntity(
            telemetriaDTO.getSsid(),
            telemetriaDTO.getEstado(),
            telemetriaDTO.getFechaReporte()
        );
        
        telemetriaRepository.save(entidad);
    }
    
    // Método para obtener todos los registros de telemetría
    public List<TelemetriaEntity> obtenerTodoElHistorial() {
        return telemetriaRepository.findAll();
    }
}
