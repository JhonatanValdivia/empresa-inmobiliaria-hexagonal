package org.academico.springcloud.msvc.cobro.infrastructure.repositories;

import org.academico.springcloud.msvc.cobro.infrastructure.entities.CobroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCobroRepository extends CrudRepository<CobroEntity, Long> {
    // Spring Data JPA proporciona automáticamente las implementaciones para estos métodos.
    // findAll() de CrudRepository devuelve Iterable, lo convertimos a List en el adaptador si es necesario.
    @Override
    List<CobroEntity> findAll(); // Sobrescribimos para obtener un List directamente
}