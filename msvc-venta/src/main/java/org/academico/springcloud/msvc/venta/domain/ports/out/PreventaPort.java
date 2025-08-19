package org.academico.springcloud.msvc.venta.domain.ports.out;

import org.academico.springcloud.msvc.venta.domain.models.valueobjects.PreventaInfo;
import java.util.Optional;

public interface PreventaPort {
    Optional<PreventaInfo> obtenerInfoPreventa(Long idPreventa);
}