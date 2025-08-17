package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.EliminarUsuarioUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EliminarUsuarioUseCaseImpl implements EliminarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public EliminarUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional
    public boolean eliminarUsuario(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        Optional<Usuario> existingUsuario = usuarioRepositoryPort.findById(id);
        if (existingUsuario.isEmpty()) {
            return false; // Devuelve false si el usuario no existe
        }
        usuarioRepositoryPort.deleteById(id);
        return true; // Devuelve true si la eliminación fue exitosa
    }
}