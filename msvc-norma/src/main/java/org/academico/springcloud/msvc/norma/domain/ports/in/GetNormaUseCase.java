package org.academico.springcloud.msvc.norma.domain.ports.in;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;

import java.util.List;
import java.util.Optional;

public interface GetNormaUseCase {
    Optional<Norma> getNorma(Long id);
    List<Norma>getAllNormas();
}
