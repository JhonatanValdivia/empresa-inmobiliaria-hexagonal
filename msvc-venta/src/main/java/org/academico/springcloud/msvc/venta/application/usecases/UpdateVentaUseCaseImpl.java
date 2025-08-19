package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.in.UpdateVentaUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UpdateVentaUseCaseImpl implements UpdateVentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;

    public UpdateVentaUseCaseImpl(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    @Transactional
    public Optional<Venta> updateVenta(Long id, Venta venta) {
        return ventaRepositoryPort.findById(id).map(existingVenta -> {
            // Actualiza los campos de la venta existente con los valores de la venta entrante
            // Esta es lógica de orquestación a nivel de aplicación
            if (venta.getTipoVenta() != null) {
                existingVenta.setTipoVenta(venta.getTipoVenta());
            }
            if (venta.getEstado() != null) {
                existingVenta.setEstado(venta.getEstado());
            }
            if (venta.getFecha() != null) {
                existingVenta.setFecha(venta.getFecha());
            }
            if (venta.getPrecioVenta() != null) {
                existingVenta.setPrecioVenta(venta.getPrecioVenta());
            }
            // Los detalles de venta se manejan en otro caso de uso (ManageDetalleVentaUseCase)
            // No se deben actualizar directamente aquí para evitar inconsistencias con el agregado.

            return ventaRepositoryPort.save(existingVenta);
        });
    }
}