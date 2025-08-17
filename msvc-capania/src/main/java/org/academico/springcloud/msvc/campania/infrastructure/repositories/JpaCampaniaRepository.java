package org.academico.springcloud.msvc.campania.infrastructure.repositories;

import org.academico.springcloud.msvc.campania.infrastructure.entities.CampaniaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCampaniaRepository extends CrudRepository<CampaniaEntity, Long> {
}