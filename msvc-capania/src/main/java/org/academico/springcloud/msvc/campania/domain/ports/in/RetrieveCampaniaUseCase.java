package org.academico.springcloud.msvc.campania.domain.ports.in;

import org.academico.springcloud.msvc.campania.domain.model.Campania;

import java.util.List;
import java.util.Optional;

public interface RetrieveCampaniaUseCase {
    List<Campania> findAll();
    Optional<Campania> findById(Long id);
}