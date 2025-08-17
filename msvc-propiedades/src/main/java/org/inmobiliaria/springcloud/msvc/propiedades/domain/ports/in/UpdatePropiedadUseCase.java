package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;

import java.util.Optional;

public interface UpdatePropiedadUseCase {
    Optional<PropiedadInmobiliaria> updatePropiedad(Long id, PropiedadInmobiliaria propiedadInmobiliaria);

}
