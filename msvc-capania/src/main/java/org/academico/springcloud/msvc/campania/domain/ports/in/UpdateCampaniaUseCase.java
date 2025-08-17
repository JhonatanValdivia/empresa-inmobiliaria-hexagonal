package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.model.Campania;

public interface UpdateCampaniaUseCase {
    Campania update(Campania campania);
}