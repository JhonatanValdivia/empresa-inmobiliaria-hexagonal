package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.GenerateComprobanteUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class GenerateComprobanteUseCaseImpl implements GenerateComprobanteUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public GenerateComprobanteUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<String> generateComprobante(Long idCobro) {
        return cobroRepositoryPort.findById(idCobro)
                .map(Cobro::generarComprobante); // Delega la lógica de generación al objeto de dominio
    }
}