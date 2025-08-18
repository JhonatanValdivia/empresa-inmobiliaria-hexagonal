package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.config;

import org.inmobiliaria.springcloud.msvc.propiedades.application.services.PropiedadService;
import org.inmobiliaria.springcloud.msvc.propiedades.application.usecases.*;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.AsignarUsuarioUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.EliminarUsuarioUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.in.GetUsuarioDetailsUseCase;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.ExternalServicePort;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.ExternalServiceAdapter;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.UsuarioClientRest;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories.JpaPropiedadRepository;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories.JpaPropiedadRepositoryAdapter;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean

    public PropiedadService propiedadService(PropiedadRepositoryPort repo,
                                             GetUsuarioDetailsUseCase getUsuarioDetailsUC,
                                             AsignarUsuarioUseCase asignarUsuarioUC,
                                             EliminarUsuarioUseCase eliminarUsuarioUC) {
        return new PropiedadService(
                new CreatePropiedadUseCaseImpl(repo),
                new DeletePropiedadUseCaseImpl(repo),
                new GetPropiedadUseCaseImpl(repo),
                new UpdatePropiedadUseCaseImpl(repo),
                getUsuarioDetailsUC,
                asignarUsuarioUC,
                eliminarUsuarioUC
        );


    }

    @Bean
    public GetUsuarioDetailsUseCase getUsuarioDetailsUseCase(ExternalServicePort externalServicePort){
        return new GetUsuarioDetailsUseCaseImpl(externalServicePort);
    }

    @Bean
    public AsignarUsuarioUseCase asignarUsuarioUseCase(PropiedadRepositoryPort repo,
                                                       ExternalServicePort externalPort) {
        return (propiedadId, usuarioId) -> {
            var prop = repo.findById(propiedadId)
                    .orElseThrow(() -> new IllegalArgumentException("Propiedad no existe"));
            // validación mínima: que el usuario remoto exista
            externalPort.getUsuarioDetails(usuarioId)
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no existe"));
            prop.asignarUsuario(usuarioId);
            repo.save(prop);
        };
    }
    @Bean
    public EliminarUsuarioUseCase eliminarUsuarioUseCase(PropiedadRepositoryPort repo) {
        return propiedadId -> {
            var prop = repo.findById(propiedadId)
                    .orElseThrow(() -> new IllegalArgumentException("Propiedad no existe"));
            prop.quitarUsuario();
            repo.save(prop);
        };
    }

    /*
    @Bean
    public ExternalServicePort externalServicePort(){
        return new ExternalServiceAdapter();
    }
     */

   /* @Bean
    public PropiedadRepositoryPort propiedadRepositoryPort(JpaPropiedadRepositoryAdapter jpaPropiedadRepositoryAdapter){
        return jpaPropiedadRepositoryAdapter;
    }

    */
}
