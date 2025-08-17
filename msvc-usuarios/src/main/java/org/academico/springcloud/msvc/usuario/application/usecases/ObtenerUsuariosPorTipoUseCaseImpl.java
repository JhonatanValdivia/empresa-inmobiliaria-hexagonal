package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.models.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.ObtenerUsuariosPorTipoUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ObtenerUsuariosPorTipoUseCaseImpl implements ObtenerUsuariosPorTipoUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ObtenerUsuariosPorTipoUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerPorTipo(TipoUsuario tipoUsuario) {
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("El tipo de usuario no puede ser nulo");
        }
        return usuarioRepositoryPort.findByTipoUsuario(tipoUsuario);
    }
}