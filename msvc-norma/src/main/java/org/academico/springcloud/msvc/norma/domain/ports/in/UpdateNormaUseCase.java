package org.academico.springcloud.msvc.norma.domain.ports.in;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;

import java.util.Optional;

public interface UpdateNormaUseCase {
    Optional<Norma> updateNorma(Long id, Norma norma);
}
