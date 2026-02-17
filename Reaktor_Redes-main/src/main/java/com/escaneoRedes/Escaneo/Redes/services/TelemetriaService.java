package com.escaneoRedes.Escaneo.Redes.services;

import com.escaneoRedes.Escaneo.Redes.dto.TelemetriaDTO;
import com.escaneoRedes.Escaneo.Redes.exceptions.CampoObligatorioException;
import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.TelemetriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TelemetriaService {
    
    @Autowired
    private TelemetriaRepository telemetriaRepository;
    
    private void validarCamposObligatorios(TelemetriaDTO telemetriaDTO) {
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
    
    public void guardarTelemetria(TelemetriaDTO telemetriaDTO) {
        validarCamposObligatorios(telemetriaDTO);
        
        TelemetriaEntity entidad = new TelemetriaEntity(
            telemetriaDTO.getSsid(),
            telemetriaDTO.getEstado(),
            telemetriaDTO.getFechaReporte()
        );
        
        telemetriaRepository.save(entidad);
    }
    
    public List<TelemetriaEntity> obtenerTodoElHistorial() {
        return telemetriaRepository.findAll();
    }
}
