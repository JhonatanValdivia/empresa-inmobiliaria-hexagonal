package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.ActualizarUsuarioUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ActualizarUsuarioUseCaseImpl implements ActualizarUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public ActualizarUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional
    public Usuario actualizarUsuario(Long id, Usuario usuario) {
        if (id == null || usuario == null) {
            throw new IllegalArgumentException("El ID y el usuario no pueden ser nulos");
        }
        Optional<Usuario> existingUsuario = usuarioRepositoryPort.findById(id);
        if (existingUsuario.isEmpty()) {
            throw new IllegalStateException("Usuario no encontrado con ID: " + id);
        }
        if (usuario.getNombreCompleto() == null) {
            throw new IllegalArgumentException("El nombre completo es obligatorio");
        }
        if (usuario.getTipoUsuario() == null) {
            throw new IllegalArgumentException("El tipo de usuario es obligatorio");
        }
        if (usuario.getTelefono() == null) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (usuario.getCorreoElectronico() == null || usuario.getCorreoElectronico().getValorCorreo().isEmpty()) {
            throw new IllegalArgumentException("El correo electrónico es obligatorio");
        }
        if (usuario.getDireccion() == null) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        usuario.setId(id); // Mantener el ID original
        return usuarioRepositoryPort.save(usuario);
    }
}