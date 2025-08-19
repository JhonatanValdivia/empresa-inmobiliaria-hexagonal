package org.academico.springcloud.msvc.cobro.application.usecases;

import org.academico.springcloud.msvc.cobro.domain.models.domainentities.Cobro;
import org.academico.springcloud.msvc.cobro.domain.ports.in.UpdateCobroUseCase;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class UpdateCobroUseCaseImpl implements UpdateCobroUseCase {

    private final CobroRepositoryPort cobroRepositoryPort;

    public UpdateCobroUseCaseImpl(CobroRepositoryPort cobroRepositoryPort) {
        this.cobroRepositoryPort = cobroRepositoryPort;
    }

    @Override
    @Transactional
    public Optional<Cobro> updateCobro(Long id, Cobro cobro) {
        return cobroRepositoryPort.findById(id).map(existingCobro -> {
            // Actualiza los campos del cobro existente con los valores del cobro entrante
            // Esto es lógica de orquestación a nivel de aplicación, no del dominio Cobro en sí
            if (cobro.getIdVenta() != null) {
                existingCobro.setIdVenta(cobro.getIdVenta());
            }
            if (cobro.getFechaCobro() != null) {
                existingCobro.setFechaCobro(cobro.getFechaCobro());
            }
            if (cobro.getMontoCobro() != null) {
                existingCobro.setMontoCobro(cobro.getMontoCobro());
            }
            if (cobro.getEstadoCobro() != null) {
                existingCobro.setEstadoCobro(cobro.getEstadoCobro());
            }
            if (cobro.getMedioPago() != null) {
                existingCobro.setMedioPago(cobro.getMedioPago());
            }

            return cobroRepositoryPort.save(existingCobro);
        });
    }
}