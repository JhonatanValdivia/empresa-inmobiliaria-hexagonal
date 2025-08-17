package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories;

import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.PropiedadInmobiliariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaPropiedadRepository extends JpaRepository<PropiedadInmobiliariaEntity, Long> {
}
