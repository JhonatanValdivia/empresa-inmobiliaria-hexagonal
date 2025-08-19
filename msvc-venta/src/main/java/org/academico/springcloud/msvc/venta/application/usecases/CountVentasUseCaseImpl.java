package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.ports.in.CountVentasUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CountVentasUseCaseImpl implements CountVentasUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;

    public CountVentasUseCaseImpl(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public long countAllVentas() {
        return ventaRepositoryPort.count();
    }
}