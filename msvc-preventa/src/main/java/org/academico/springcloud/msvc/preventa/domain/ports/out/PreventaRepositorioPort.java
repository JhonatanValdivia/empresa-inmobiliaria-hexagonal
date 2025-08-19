package org.academico.springcloud.msvc.preventa.domain.ports.out;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa;

import java.util.List;
import java.util.Optional;

public interface PreventaRepositorioPort {
    Preventa guardar(Preventa preventa);
    Optional<Preventa> porId(Long id);
    List<Preventa> listar();
    Optional<Preventa> actualizar(Preventa preventa); // Aquí se usa Optional<Preventa> para devolver el actualizado o empty
    void eliminarPorId(Long id);
    boolean existsById(Long id); // Añadido para consistencia con otros repositorios

    List<Preventa> buscarPorIdPropiedadYEstado(Long idPropiedad, EstadoPreventa estado);
    boolean existePorUsuarioClienteIdYEstado(Long usuarioClienteId, EstadoPreventa estado);
    void eliminarPorIds(List<Long> ids);
}