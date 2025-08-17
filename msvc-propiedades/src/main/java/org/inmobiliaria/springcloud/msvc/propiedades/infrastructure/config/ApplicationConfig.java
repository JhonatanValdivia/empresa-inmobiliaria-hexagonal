package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.config;

import org.inmobiliaria.springcloud.msvc.propiedades.application.services.PropiedadService;
import org.inmobiliaria.springcloud.msvc.propiedades.application.usecases.CreatePropiedadUseCaseImpl;
import org.inmobiliaria.springcloud.msvc.propiedades.application.usecases.DeletePropiedadUseCaseImpl;
import org.inmobiliaria.springcloud.msvc.propiedades.application.usecases.GetPropiedadUseCaseImpl;
import org.inmobiliaria.springcloud.msvc.propiedades.application.usecases.UpdatePropiedadUseCaseImpl;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.PropiedadRepositoryPort;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories.JpaPropiedadRepository;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.repositories.JpaPropiedadRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PropiedadService propiedadService(PropiedadRepositoryPort propiedadRepositoryPort){
        return new PropiedadService(
                new CreatePropiedadUseCaseImpl(propiedadRepositoryPort),
                new DeletePropiedadUseCaseImpl(propiedadRepositoryPort),
                new GetPropiedadUseCaseImpl(propiedadRepositoryPort),
                new UpdatePropiedadUseCaseImpl(propiedadRepositoryPort)
        );


    }
   /* @Bean
    public PropiedadRepositoryPort propiedadRepositoryPort(JpaPropiedadRepositoryAdapter jpaPropiedadRepositoryAdapter){
        return jpaPropiedadRepositoryAdapter;
    }

    */
}
