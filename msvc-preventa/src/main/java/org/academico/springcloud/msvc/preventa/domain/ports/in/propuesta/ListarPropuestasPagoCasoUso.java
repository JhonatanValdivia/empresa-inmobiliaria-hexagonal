package org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import java.util.List;
import java.util.Optional;

public interface ListarPropuestasPagoCasoUso {
    Optional<List<PropuestaPago>> listarPropuestasPagoPorPreventa(Long preventaId);
}