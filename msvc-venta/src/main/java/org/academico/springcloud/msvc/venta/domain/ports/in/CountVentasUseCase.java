package org.academico.springcloud.msvc.venta.domain.ports.in; // <- PUERTOS DE ENTRADA VAN EN EL DOMINIO

public interface CountVentasUseCase {
    long countAllVentas();
}