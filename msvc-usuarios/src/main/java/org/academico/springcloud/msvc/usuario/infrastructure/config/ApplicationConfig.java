package org.academico.springcloud.msvc.usuario.infrastructure.config;

import org.academico.springcloud.msvc.usuario.application.usecases.*;
import org.academico.springcloud.msvc.usuario.domain.ports.in.*;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.academico.springcloud.msvc.usuario.infrastructure.adapters.JpaUsuarioRepositoryAdapter;
import org.academico.springcloud.msvc.usuario.infrastructure.mappers.UsuarioMapper;
import org.academico.springcloud.msvc.usuario.infrastructure.repositories.JpaUsuarioRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public CrearUsuarioUseCase crearUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new CrearUsuarioUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public ObtenerUsuarioUseCase obtenerUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new ObtenerUsuarioUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public ActualizarUsuarioUseCase actualizarUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new ActualizarUsuarioUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public EliminarUsuarioUseCase eliminarUsuarioUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new EliminarUsuarioUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public ContarUsuariosUseCase contarUsuariosUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new ContarUsuariosUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public ObtenerUsuariosPorTipoUseCase obtenerUsuariosPorTipoUseCase(UsuarioRepositoryPort usuarioRepositoryPort) {
        return new ObtenerUsuariosPorTipoUseCaseImpl(usuarioRepositoryPort);
    }

    @Bean
    public UsuarioRepositoryPort usuarioRepositoryPort(JpaUsuarioRepository jpaRepository, UsuarioMapper usuarioMapper) {
        return new JpaUsuarioRepositoryAdapter(jpaRepository, usuarioMapper);
    }

    @Bean
    public UsuarioMapper usuarioMapper() {
        return new UsuarioMapper();
    }
}