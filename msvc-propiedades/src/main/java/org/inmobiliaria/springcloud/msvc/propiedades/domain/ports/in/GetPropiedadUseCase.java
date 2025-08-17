package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;

import java.util.List;
import java.util.Optional;

public interface GetPropiedadUseCase {
    Optional<PropiedadInmobiliaria> getPropiedad(Long id);
    List<PropiedadInmobiliaria> getAllPropiedades();
}
