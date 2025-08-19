package org.academico.springcloud.msvc.venta.domain.ports.in; // <- PUERTOS DE ENTRADA VAN EN EL DOMINIO

import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;

import java.util.Optional;

public interface ManageDetalleVentaUseCase {
    Optional<Venta> addDetalleVenta(Long ventaId, DetalleVenta detalleVenta);
    boolean removeDetalleVenta(Long ventaId, Long detalleVentaId);
}