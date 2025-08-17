package org.academico.springcloud.msvc.campania.infrastructure.repositories;

import org.academico.springcloud.msvc.campania.infrastructure.models.entities.CampaniaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaniaRepository extends CrudRepository<CampaniaEntity, Long> {
}

