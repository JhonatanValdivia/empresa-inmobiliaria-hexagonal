package org.academico.springcloud.msvc.cobro.infrastructure.config;

import org.academico.springcloud.msvc.cobro.application.services.CobroService;
import org.academico.springcloud.msvc.cobro.application.usecases.*;
import org.academico.springcloud.msvc.cobro.domain.ports.in.*;
import org.academico.springcloud.msvc.cobro.domain.ports.out.CobroRepositoryPort;
import org.academico.springcloud.msvc.cobro.infrastructure.adapters.JpaCobroRepositoryAdapter;
import org.academico.springcloud.msvc.cobro.infrastructure.repositories.JpaCobroRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CobroService cobroService(
            CreateCobroUseCase createCobroUseCase,
            DeleteCobroUseCase deleteCobroUseCase,
            GenerateComprobanteUseCase generateComprobanteUseCase,
            GetCobroUseCase getCobroUseCase,
            RegistrarPagoUseCase registrarPagoUseCase,
            UpdateCobroUseCase updateCobroUseCase) {
        return new CobroService(
                createCobroUseCase,
                deleteCobroUseCase,
                generateComprobanteUseCase,
                getCobroUseCase,
                registrarPagoUseCase,
                updateCobroUseCase
        );
    }

    // Definición de los Use Cases (Application Layer)
    @Bean
    public CreateCobroUseCase createCobroUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new CreateCobroUseCaseImpl(cobroRepositoryPort);
    }

    @Bean
    public DeleteCobroUseCase deleteCobroUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new DeleteCobroUseCaseImpl(cobroRepositoryPort);
    }

    @Bean
    public GenerateComprobanteUseCase generateComprobanteUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new GenerateComprobanteUseCaseImpl(cobroRepositoryPort);
    }

    @Bean
    public GetCobroUseCase getCobroUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new GetCobroUseCaseImpl(cobroRepositoryPort);
    }

    @Bean
    public RegistrarPagoUseCase registrarPagoUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new RegistrarPagoUseCaseImpl(cobroRepositoryPort);
    }

    @Bean
    public UpdateCobroUseCase updateCobroUseCase(CobroRepositoryPort cobroRepositoryPort) {
        return new UpdateCobroUseCaseImpl(cobroRepositoryPort);
    }

    // Definición del Adaptador de persistencia (Infrastructure Layer)
    @Bean
    public CobroRepositoryPort cobroRepositoryPort(JpaCobroRepository jpaCobroRepository) {
        return new JpaCobroRepositoryAdapter(jpaCobroRepository);
    }
}