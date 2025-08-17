package org.academico.springcloud.msvc.norma.domain.ports.out;

import org.academico.springcloud.msvc.norma.domain.models.entities.Norma;

import java.util.List;
import java.util.Optional;

public interface NormaRepositoryPort {
    Norma save(Norma norma);
    Optional<Norma> findById(Long id);
    List<Norma> findAll();
    Optional<Norma> update(Norma norma);
    boolean deleteById(Long id);
}
