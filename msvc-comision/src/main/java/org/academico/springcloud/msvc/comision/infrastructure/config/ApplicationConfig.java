package org.academico.springcloud.msvc.comision.infrastructure.config;

import org.academico.springcloud.msvc.comision.application.services.ComisionServicio;
import org.academico.springcloud.msvc.comision.application.usescases.ActualizarComisionCasoUsoImpl;
import org.academico.springcloud.msvc.comision.application.usescases.CrearComisionCasoUsoImpl;
import org.academico.springcloud.msvc.comision.application.usescases.EliminarComisionCasoUsoImpl;
import org.academico.springcloud.msvc.comision.application.usescases.RecuperarComisionCasoUsoImpl;
import org.academico.springcloud.msvc.comision.domain.ports.out.ComisionRepositorioPort;
import org.academico.springcloud.msvc.comision.domain.ports.out.ExternalServicePort;
import org.academico.springcloud.msvc.comision.infrastructure.adapters.ExternalServiceAdapter;
import org.academico.springcloud.msvc.comision.infrastructure.repositories.ComisionRepositorioAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public ComisionServicio preventaServicio(ComisionRepositorioPort comisionRepositorioPort) {
        return new ComisionServicio(
                new CrearComisionCasoUsoImpl(comisionRepositorioPort),
                new RecuperarComisionCasoUsoImpl(comisionRepositorioPort),
                new ActualizarComisionCasoUsoImpl(comisionRepositorioPort),
                new EliminarComisionCasoUsoImpl(comisionRepositorioPort)
        );
    }

    @Bean
    public ComisionRepositorioPort comisionRepositorioPort(ComisionRepositorioAdapter comisionRepositorioAdapter) {
        return comisionRepositorioAdapter;
    }

    @Bean
    public ExternalServicePort externalServicePort() {
        return new ExternalServiceAdapter();
    }
}
