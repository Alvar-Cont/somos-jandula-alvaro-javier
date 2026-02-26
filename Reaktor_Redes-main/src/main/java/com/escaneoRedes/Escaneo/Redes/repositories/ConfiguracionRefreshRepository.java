package com.escaneoRedes.Escaneo.Redes.repositories;

import com.escaneoRedes.Escaneo.Redes.models.ConfiguracionRefreshEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracionRefreshRepository extends JpaRepository<ConfiguracionRefreshEntity, Long> {

    Optional<ConfiguracionRefreshEntity> findByEmail(String email);
}
