package org.academico.springcloud.msvc.venta.infrastructure.repositories;

import org.academico.springcloud.msvc.venta.infrastructure.entities.VentaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaVentaRepository extends CrudRepository<VentaEntity, Long> {
    @Override
    List<VentaEntity> findAll();
    List<VentaEntity> findAllById(Iterable<Long> ids);
}