package com.escaneoRedes.Escaneo.Redes.services;

import com.escaneoRedes.Escaneo.Redes.dto.ConfiguracionRefreshDTO;
import com.escaneoRedes.Escaneo.Redes.models.ConfiguracionRefreshEntity;
import com.escaneoRedes.Escaneo.Redes.repositories.ConfiguracionRefreshRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionRefreshService {

    private static final String CLAVE_GLOBAL = "GLOBAL";
    private static final boolean AUTO_REFRESH_POR_DEFECTO = true;
    private static final int INTERVALO_POR_DEFECTO = 30;

    @Autowired
    private ConfiguracionRefreshRepository configuracionRefreshRepository;

    public ConfiguracionRefreshDTO obtenerConfiguracionGlobal() {
        return configuracionRefreshRepository
                .findByEmail(CLAVE_GLOBAL)
                .map(this::toDto)
                .orElseGet(this::configuracionPorDefecto);
    }

    public ConfiguracionRefreshDTO guardarConfiguracionGlobal(ConfiguracionRefreshDTO dto) {
        ConfiguracionRefreshEntity entity = configuracionRefreshRepository
                .findByEmail(CLAVE_GLOBAL)
                .orElseGet(ConfiguracionRefreshEntity::new);

        entity.setEmail(CLAVE_GLOBAL);
        entity.setAutoRefresh(normalizarAutoRefresh(dto));
        entity.setRefreshInterval(normalizarIntervalo(dto));

        ConfiguracionRefreshEntity guardada = configuracionRefreshRepository.save(entity);
        return toDto(guardada);
    }

    private ConfiguracionRefreshDTO toDto(ConfiguracionRefreshEntity entity) {
        return new ConfiguracionRefreshDTO(entity.getAutoRefresh(), entity.getRefreshInterval());
    }

    private ConfiguracionRefreshDTO configuracionPorDefecto() {
        return new ConfiguracionRefreshDTO(AUTO_REFRESH_POR_DEFECTO, INTERVALO_POR_DEFECTO);
    }

    private Boolean normalizarAutoRefresh(ConfiguracionRefreshDTO dto) {
        if (dto == null || dto.getAutoRefresh() == null) {
            return AUTO_REFRESH_POR_DEFECTO;
        }
        return dto.getAutoRefresh();
    }

    private Integer normalizarIntervalo(ConfiguracionRefreshDTO dto) {
        if (dto == null || dto.getRefreshInterval() == null || dto.getRefreshInterval() < 1) {
            return INTERVALO_POR_DEFECTO;
        }
        return dto.getRefreshInterval();
    }
}
