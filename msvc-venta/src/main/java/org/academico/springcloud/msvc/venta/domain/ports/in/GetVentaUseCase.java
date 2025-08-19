package org.academico.springcloud.msvc.venta.domain.ports.in; // <- PUERTOS DE ENTRADA VAN EN EL DOMINIO

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;

import java.util.List;
import java.util.Optional;

public interface GetVentaUseCase {
    Optional<Venta> getVentaById(Long id);
    List<Venta> getAllVentas();
    List<Venta> getVentasByIds(List<Long> ids);
}