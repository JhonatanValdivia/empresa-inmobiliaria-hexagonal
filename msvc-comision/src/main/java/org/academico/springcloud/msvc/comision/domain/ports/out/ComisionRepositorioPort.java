package org.academico.springcloud.msvc.comision.domain.ports.out;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;

import java.util.List;
import java.util.Optional;

public interface ComisionRepositorioPort {
    List<Comision> listar();
    Optional<Comision> porId(Long id);
    Comision guardar(Comision comision);
    boolean eliminarPorId(Long id);
    Optional<Comision> actualizar(Comision comision);
    boolean existePorId(Long id);
    long contarComisiones();
}
