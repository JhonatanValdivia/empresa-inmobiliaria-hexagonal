package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;

import java.util.List;
import java.util.Optional;

public interface PropiedadRepositoryPort {
    PropiedadInmobiliaria save(PropiedadInmobiliaria propiedadInmobiliaria);
    Optional<PropiedadInmobiliaria> findById(Long id);
    List<PropiedadInmobiliaria> findAll();
    Optional<PropiedadInmobiliaria> update(PropiedadInmobiliaria propiedadInmobiliaria);
    boolean deleteById(Long id);


}
