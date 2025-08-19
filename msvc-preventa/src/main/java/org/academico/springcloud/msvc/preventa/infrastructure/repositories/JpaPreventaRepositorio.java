package org.academico.springcloud.msvc.preventa.infrastructure.repositories;

import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa;
import org.academico.springcloud.msvc.preventa.infrastructure.entities.PreventaEntidad;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPreventaRepositorio extends CrudRepository<PreventaEntidad, Long> {
    @Override
    List<PreventaEntidad> findAll();

    List<PreventaEntidad> findByIdPropiedadAndEstado(Long idPropiedad, EstadoPreventa estado);
    boolean existsByUsuarioClienteIdAndEstado(Long usuarioClienteId, EstadoPreventa estado);

    @Modifying
    @Query("DELETE FROM PreventaEntidad p WHERE p.id IN ?1")
    void eliminarPorIds(List<Long> ids);
}