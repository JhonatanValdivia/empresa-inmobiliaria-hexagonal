package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.models.domainentities.Venta;
import org.academico.springcloud.msvc.venta.domain.ports.in.GetVentaUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class GetVentaUseCaseImpl implements GetVentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;


    public GetVentaUseCaseImpl(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> getVentaById(Long id) {
        return ventaRepositoryPort.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getAllVentas() {
        return ventaRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getVentasByIds(List<Long> ids) {
        return ventaRepositoryPort.findAllById(ids);
    }
}