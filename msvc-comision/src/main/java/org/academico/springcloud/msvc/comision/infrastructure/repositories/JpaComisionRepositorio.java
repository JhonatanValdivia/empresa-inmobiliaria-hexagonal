package org.academico.springcloud.msvc.comision.infrastructure.repositories;

import org.academico.springcloud.msvc.comision.infrastructure.models.entities.ComisionEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaComisionRepositorio extends JpaRepository<ComisionEntidad, Long> {
}
