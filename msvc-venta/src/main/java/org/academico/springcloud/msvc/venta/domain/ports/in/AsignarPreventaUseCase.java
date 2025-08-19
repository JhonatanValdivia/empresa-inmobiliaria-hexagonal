package org.academico.springcloud.msvc.venta.domain.ports.in;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import java.util.Optional;

public interface AsignarPreventaUseCase {
    Optional<Venta> asignarPreventaAVenta(Long idVenta, Long idPreventa);
}