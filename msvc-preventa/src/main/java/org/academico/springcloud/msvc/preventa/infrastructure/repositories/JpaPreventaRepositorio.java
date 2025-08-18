package org.academico.springcloud.msvc.preventa.infrastructure.repositories;

import org.academico.springcloud.msvc.preventa.infrastructure.entities.PreventaEntidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaPreventaRepositorio extends CrudRepository<PreventaEntidad, Long> {
    @Override
    List<PreventaEntidad> findAll(); // Para obtener un List directamente
    // Puedes añadir métodos de consulta personalizados aquí si fueran necesarios
}