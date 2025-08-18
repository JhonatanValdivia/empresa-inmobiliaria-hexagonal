package org.academico.springcloud.msvc.comision.domain.ports.in;

import org.academico.springcloud.msvc.comision.domain.models.entities.Comision;

import java.util.List;
import java.util.Optional;

public interface RecuperarComisionCasoUso {
    Optional<Comision> comisionPorId(Long id);
    List<Comision> obtenerTodasComisiones();
}
