package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.ports.in.ContarUsuariosUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContarUsuariosUseCaseImpl implements ContarUsuariosUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ContarUsuariosUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public long contarUsuarios() {
        return usuarioRepositoryPort.count();
    }
}