package org.academico.springcloud.msvc.venta.domain.ports.in; // <- PUERTOS DE ENTRADA VAN EN EL DOMINIO

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;

public interface CreateVentaUseCase {
    Venta createVenta(Venta venta);
}