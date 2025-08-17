package org.academico.springcloud.msvc.campania.infrastructure.config;

import org.academico.springcloud.msvc.campania.application.usecases.*;
import org.academico.springcloud.msvc.campania.domain.ports.in.*;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.academico.springcloud.msvc.campania.domain.ports.out.PropiedadExternalServicePort;
import org.academico.springcloud.msvc.campania.infrastructure.adapters.external.PropiedadExternalServiceAdapter;
import org.academico.springcloud.msvc.campania.infrastructure.clients.PropiedadClientRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Bean
    public CreateCampaniaUseCase createCampaniaUseCase(
            CampaniaRepositoryPort repositoryPort,
            PropiedadExternalServicePort externalPort) { // <-- inyecta la interfaz
        return new CreateCampaniaUseCaseImpl(repositoryPort, externalPort);
    }

    @Bean
    public RetrieveCampaniaUseCase retrieveCampaniaUseCase(CampaniaRepositoryPort repositoryPort) {
        return new RetrieveCampaniaUseCaseImpl(repositoryPort);
    }

    @Bean
    public UpdateCampaniaUseCase updateCampaniaUseCase(CampaniaRepositoryPort repositoryPort) {
        return new UpdateCampaniaUseCaseImpl(repositoryPort);
    }

    @Bean
    public DeleteCampaniaUseCase deleteCampaniaUseCase(CampaniaRepositoryPort repositoryPort) {
        return new DeleteCampaniaUseCaseImpl(repositoryPort);
    }

    @Bean
    public ApproveMontoUseCase approveMontoUseCase(CampaniaRepositoryPort repositoryPort) {
        return new ApproveMontoUseCaseImpl(repositoryPort);
    }

    @Bean
    public ManageProveedorUseCase manageProveedorUseCase(CampaniaRepositoryPort repositoryPort) {
        return new ManageProveedorUseCaseImpl(repositoryPort);
    }

    @Bean
    public PropiedadExternalServicePort propiedadExternalServicePort(PropiedadClientRest clientRest) {
        return new PropiedadExternalServiceAdapter(clientRest);
    }



}
