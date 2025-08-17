package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

public interface ActualizarCampaniaUseCase {
    Campania update(Campania campania);
}