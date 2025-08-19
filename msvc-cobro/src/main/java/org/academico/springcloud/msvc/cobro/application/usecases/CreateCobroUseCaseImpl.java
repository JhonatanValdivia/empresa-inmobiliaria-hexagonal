package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.CreateCobroUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

public class CreateCobroUseCaseImpl implements CreateCobroUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public CreateCobroUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional
    public Cobro createCobro(Cobro cobro) {
        // Validación de negocio adicional si es necesaria antes de guardar
        if (cobro.getIdVenta() == null) {
            throw new IllegalArgumentException("El ID de la venta es obligatorio para crear un cobro.");
        }
        return cobroRepositoryPort.save(cobro);
    }
}