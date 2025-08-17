package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

public interface CrearCampaniaUseCase {
    Campania create(Campania campania);
}