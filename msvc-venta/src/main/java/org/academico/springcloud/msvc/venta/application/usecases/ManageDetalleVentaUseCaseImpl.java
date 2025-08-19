package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.in.ManageDetalleVentaUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class ManageDetalleVentaUseCaseImpl implements ManageDetalleVentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;


    public ManageDetalleVentaUseCaseImpl(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    @Transactional
    public Optional<Venta> addDetalleVenta(Long ventaId, DetalleVenta detalleVenta) {
        return ventaRepositoryPort.findById(ventaId).map(venta -> {
            try {
                venta.agregarDetalleVenta(detalleVenta); // Lógica de agregado en el dominio
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Error al agregar detalle a la venta: " + e.getMessage(), e);
            }
            return ventaRepositoryPort.save(venta);
        });
    }

    @Override
    @Transactional
    public boolean removeDetalleVenta(Long ventaId, Long detalleVentaId) {
        Optional<Venta> optionalVenta = ventaRepositoryPort.findById(ventaId);
        if (optionalVenta.isPresent()) {
            Venta venta = optionalVenta.get();
            try {
                venta.eliminarDetalleVenta(detalleVentaId); // Lógica de agregado en el dominio
            } catch (IllegalArgumentException e) {
                // Si no se encuentra el detalle en la venta, o algún otro error del dominio
                throw new RuntimeException("Error al eliminar detalle de la venta: " + e.getMessage(), e);
            }
            ventaRepositoryPort.save(venta);
            return true;
        }
        return false;
    }
}