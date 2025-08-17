package org.academico.springcloud.msvc.campania.application.usecases;

import org.academico.springcloud.msvc.campania.domain.model.Campania;
import org.academico.springcloud.msvc.campania.domain.ports.in.ApproveMontoUseCase;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ApproveMontoUseCaseImpl implements ApproveMontoUseCase {
    private final CampaniaRepositoryPort repositoryPort;

    public ApproveMontoUseCaseImpl(CampaniaRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public Campania approve(Long id, BigDecimal nuevoMonto) {
        Optional<Campania> campaniaOpt = repositoryPort.findById(id);
        if (campaniaOpt.isEmpty()) {
            throw new IllegalArgumentException("La campaña con ID " + id + " no existe.");
        }
        Campania campania = campaniaOpt.get();
        campania.aprobarMonto(nuevoMonto);
        return repositoryPort.save(campania);
    }
}