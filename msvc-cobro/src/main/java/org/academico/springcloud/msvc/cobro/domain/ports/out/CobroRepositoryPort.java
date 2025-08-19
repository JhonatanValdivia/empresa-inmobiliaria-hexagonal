package org.academico.springcloud.msvc.cobro.domain.ports.out;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;

import java.util.List;
import java.util.Optional;

public interface CobroRepositoryPort {
    Cobro save(Cobro cobro);
    Optional<Cobro> findById(Long id);
    List<Cobro> findAll();
    void deleteById(Long id);
    boolean existsById(Long id); // Conveniencia para verificar existencia
}