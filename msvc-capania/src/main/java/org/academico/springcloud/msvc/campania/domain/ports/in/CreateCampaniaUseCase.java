package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.model.Campania;

public interface CreateCampaniaUseCase {
    Campania create(Campania campania);
}