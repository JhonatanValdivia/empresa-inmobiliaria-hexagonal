package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.in.AsignarPreventaUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.academico.springcloud.msvc.venta.domain.ports.out.PreventaPort; // NUEVO IMPORT
import org.academico.springcloud.msvc.venta.domain.models.valueobjects.PreventaInfo; // NUEVO IMPORT
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AsignarPreventaUseCaseImpl implements AsignarPreventaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;
    private final PreventaPort preventaPort; // NUEVO: Inyección del puerto externo

    public AsignarPreventaUseCaseImpl(VentaRepositoryPort ventaRepositoryPort, PreventaPort preventaPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
        this.preventaPort = preventaPort;
    }

    @Override
    @Transactional
    public Optional<Venta> asignarPreventaAVenta(Long idVenta, Long idPreventa) {
        return ventaRepositoryPort.findById(idVenta).map(venta -> {
            // 1. Obtener información de la Preventa del otro microservicio
            Optional<PreventaInfo> opPreventaInfo = preventaPort.obtenerInfoPreventa(idPreventa);

            if (opPreventaInfo.isEmpty()) {
                throw new IllegalArgumentException("Preventa no encontrada con ID: " + idPreventa);
            }

            PreventaInfo preventaInfo = opPreventaInfo.get();

            // 2. Aplicar lógica de negocio del dominio Venta
            try {
                venta.asignarPreventa(idPreventa, preventaInfo.estado()); // Pasa el estado de la preventa para validación
            } catch (IllegalStateException | IllegalArgumentException e) {
                // Envuelve la excepción de dominio en una RuntimeException para que Spring la maneje
                throw new RuntimeException("Error al asignar preventa a venta: " + e.getMessage(), e);
            }

            // 3. Guardar el estado actualizado de la Venta
            return ventaRepositoryPort.save(venta);
        });
    }
}