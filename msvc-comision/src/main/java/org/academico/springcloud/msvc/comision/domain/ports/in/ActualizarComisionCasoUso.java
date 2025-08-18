package org.academico.springcloud.msvc.comision.domain.ports.in;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;

import java.util.Optional;

public interface ActualizarComisionCasoUso {
    Optional<Comision> actualizarComision(Long id, Comision comisionActualizada);
}
