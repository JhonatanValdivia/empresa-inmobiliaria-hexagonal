package org.academico.springcloud.msvc.norma.domain.ports.in;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;

public interface CreateNormaUseCase {
    Norma createNorma(Norma norma);
}
