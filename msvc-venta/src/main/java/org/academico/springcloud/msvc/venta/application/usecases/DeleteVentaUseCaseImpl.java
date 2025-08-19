package org.academico.springcloud.msvc.venta.application.usecases;

import org.academico.springcloud.msvc.venta.domain.ports.in.DeleteVentaUseCase;
import org.academico.springcloud.msvc.venta.domain.ports.out.VentaRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class DeleteVentaUseCaseImpl implements DeleteVentaUseCase {

    private final VentaRepositoryPort ventaRepositoryPort;



    public DeleteVentaUseCaseImpl(VentaRepositoryPort ventaRepositoryPort) {
        this.ventaRepositoryPort = ventaRepositoryPort;
    }

    @Override
    @Transactional
    public boolean deleteVenta(Long id) {
        if (ventaRepositoryPort.existsById(id)) {
            ventaRepositoryPort.deleteById(id);
            return true;
        }
        return false;
    }
}