package org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.PropiedadInmobiliaria;

public interface CreatePropiedadUseCase {
    PropiedadInmobiliaria createPropiedad(PropiedadInmobiliaria propiedadInmobiliaria);
}
