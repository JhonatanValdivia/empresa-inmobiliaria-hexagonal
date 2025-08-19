package org.academico.springcloud.msvc.venta.application.services;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.DetalleVenta;
import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.in.*; // Importa todos los use cases
// NUEVO IMPORT
import org.academico.springcloud.msvc.venta.domain.ports.in.AsignarPreventaUseCase;

import java.util.List;
import java.util.Optional;

public class VentaService implements
        CreateVentaUseCase,
        DeleteVentaUseCase,
        GetVentaUseCase,
        UpdateVentaUseCase,
        ManageDetalleVentaUseCase,
        CountVentasUseCase,
        AsignarPreventaUseCase { // MODIFICADO: Implementa el nuevo caso de uso

    private final CreateVentaUseCase createVentaUseCase;
    private final DeleteVentaUseCase deleteVentaUseCase;
    private final GetVentaUseCase getVentaUseCase;
    private final UpdateVentaUseCase updateVentaUseCase;
    private final ManageDetalleVentaUseCase manageDetalleVentaUseCase;
    private final CountVentasUseCase countVentasUseCase;
    private final AsignarPreventaUseCase asignarPreventaUseCase; // NUEVO: Campo para el nuevo caso de uso

    // MODIFICADO: Constructor con el nuevo parámetro
    public VentaService(
            CreateVentaUseCase createVentaUseCase,
            DeleteVentaUseCase deleteVentaUseCase,
            GetVentaUseCase getVentaUseCase,
            UpdateVentaUseCase updateVentaUseCase,
            ManageDetalleVentaUseCase manageDetalleVentaUseCase,
            CountVentasUseCase countVentasUseCase,
            AsignarPreventaUseCase asignarPreventaUseCase) { // NUEVO PARÁMETRO
        this.createVentaUseCase = createVentaUseCase;
        this.deleteVentaUseCase = deleteVentaUseCase;
        this.getVentaUseCase = getVentaUseCase;
        this.updateVentaUseCase = updateVentaUseCase;
        this.manageDetalleVentaUseCase = manageDetalleVentaUseCase;
        this.countVentasUseCase = countVentasUseCase;
        this.asignarPreventaUseCase = asignarPreventaUseCase; // NUEVO: Asignación
    }

    @Override
    public Venta createVenta(Venta venta) {
        return createVentaUseCase.createVenta(venta);
    }

    @Override
    public boolean deleteVenta(Long id) {
        return deleteVentaUseCase.deleteVenta(id);
    }

    @Override
    public Optional<Venta> getVentaById(Long id) {
        return getVentaUseCase.getVentaById(id);
    }

    @Override
    public List<Venta> getAllVentas() {
        return getVentaUseCase.getAllVentas();
    }

    @Override
    public List<Venta> getVentasByIds(List<Long> ids) {
        return getVentaUseCase.getVentasByIds(ids);
    }

    @Override
    public Optional<Venta> updateVenta(Long id, Venta venta) {
        return updateVentaUseCase.updateVenta(id, venta);
    }

    @Override
    public Optional<Venta> addDetalleVenta(Long ventaId, DetalleVenta detalleVenta) {
        return manageDetalleVentaUseCase.addDetalleVenta(ventaId, detalleVenta);
    }

    @Override
    public boolean removeDetalleVenta(Long ventaId, Long detalleVentaId) {
        return manageDetalleVentaUseCase.removeDetalleVenta(ventaId, detalleVentaId);
    }

    @Override
    public long countAllVentas() {
        return countVentasUseCase.countAllVentas();
    }

    @Override // NUEVO MÉTODO
    public Optional<Venta> asignarPreventaAVenta(Long idVenta, Long idPreventa) {
        return asignarPreventaUseCase.asignarPreventaAVenta(idVenta, idPreventa);
    }
}