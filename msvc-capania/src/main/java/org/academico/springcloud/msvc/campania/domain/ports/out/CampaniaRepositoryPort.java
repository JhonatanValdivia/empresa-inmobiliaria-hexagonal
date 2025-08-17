package org.academico.springcloud.msvc.campania.domain.ports.out;

import org.academico.springcloud.msvc.campania.domain.models.entities.Campania;

import java.util.List;
import java.util.Optional;

public interface CampaniaRepositoryPort {
    List<Campania> findAll();
    Optional<Campania> findById(Long id);
    Campania save(Campania campania);
    void deleteById(Long id);
}