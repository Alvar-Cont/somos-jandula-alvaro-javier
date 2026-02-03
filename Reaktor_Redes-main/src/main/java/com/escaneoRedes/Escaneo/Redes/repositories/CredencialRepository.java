package com.escaneoRedes.Escaneo.Redes.repositories;

import com.escaneoRedes.Escaneo.Redes.models.CredencialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialRepository extends JpaRepository<CredencialEntity, Long> {
}