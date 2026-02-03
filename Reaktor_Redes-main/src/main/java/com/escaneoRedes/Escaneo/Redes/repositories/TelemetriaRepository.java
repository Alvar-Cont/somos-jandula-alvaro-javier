package com.escaneoRedes.Escaneo.Redes.repositories;

import com.escaneoRedes.Escaneo.Redes.models.TelemetriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelemetriaRepository extends JpaRepository<TelemetriaEntity, Long> {
}