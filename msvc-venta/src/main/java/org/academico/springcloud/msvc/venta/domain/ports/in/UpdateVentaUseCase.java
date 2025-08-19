package org.academico.springcloud.msvc.venta.domain.ports.in; // <- PUERTOS DE ENTRADA VAN EN EL DOMINIO

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;

import java.util.Optional;

public interface UpdateVentaUseCase {
    Optional<Venta> updateVenta(Long id, Venta venta);
}