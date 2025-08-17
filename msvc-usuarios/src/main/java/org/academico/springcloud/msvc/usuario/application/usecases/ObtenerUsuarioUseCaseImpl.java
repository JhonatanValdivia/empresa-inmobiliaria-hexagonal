package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.ObtenerUsuarioUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ObtenerUsuarioUseCaseImpl implements ObtenerUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ObtenerUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioRepositoryPort.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return usuarioRepositoryPort.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> obtenerPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("La lista de IDs no puede ser nula o vacía");
        }
        return usuarioRepositoryPort.findAllById(ids);
    }
}