package org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import java.util.Optional;

public interface ActualizarPropuestaPagoCasoUso {
    Optional<PropuestaPago> actualizarPropuestaPago(Long preventaId, Long propuestaId, PropuestaPago propuestaActualizada);
}