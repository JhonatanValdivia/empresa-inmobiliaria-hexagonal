package org.academico.springcloud.msvc.campania.infrastructure.config;

import org.academico.springcloud.msvc.campania.application.usecases.*;
import org.academico.springcloud.msvc.campania.domain.ports.in.*;
import org.academico.springcloud.msvc.campania.domain.ports.out.CampaniaRepositoryPort;
import org.academico.springcloud.msvc.campania.domain.ports.out.PropiedadExternalServicePort;
import org.academico.springcloud.msvc.campania.infrastructure.adapters.external.PropiedadExternalServiceAdapter;
import org.academico.springcloud.msvc.campania.infrastructure.clients.PropiedadClientRest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CrearCampaniaUseCase createCampaniaUseCase(
            CampaniaRepositoryPort repositoryPort,
            PropiedadExternalServicePort externalPort) { // <-- inyecta la interfaz
        return new CrearCampaniaUseCaselmpl(repositoryPort, externalPort);
    }

    @Bean
    public ActualizarCampaniaUseCase updateCampaniaUseCase(CampaniaRepositoryPort repositoryPort) {
        return new ActualizarCampaniaUseCaseImpl(repositoryPort);
    }

    @Bean
    public EliminarCampaniaUseCase deleteCampaniaUseCase(CampaniaRepositoryPort repositoryPort) {
        return new EliminarCampaniaUseCaseImpl(repositoryPort);
    }

    @Bean
    public AprobarMontoUseCase approveMontoUseCase(CampaniaRepositoryPort repositoryPort) {
        return new AprobarMontoUseCaselmpl(repositoryPort);
    }

    @Bean
    public GestionarProveedorUseCase manageProveedorUseCase(CampaniaRepositoryPort repositoryPort) {
        return new GestionarProveedorUseCaseImpl(repositoryPort);
    }

    @Bean
    public PropiedadExternalServicePort propiedadExternalServicePort(PropiedadClientRest clientRest) {
        return new PropiedadExternalServiceAdapter(clientRest);
    }
}
