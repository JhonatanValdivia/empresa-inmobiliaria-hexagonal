package org.academico.springcloud.msvc.campania.domain.ports.out;

import org.academico.springcloud.msvc.campania.domain.model.Propiedad;

public interface PropiedadExternalServicePort {
    Propiedad findById(Long id);
}