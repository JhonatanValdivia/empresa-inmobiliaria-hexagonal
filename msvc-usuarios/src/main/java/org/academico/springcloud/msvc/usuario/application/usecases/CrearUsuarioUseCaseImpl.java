package org.academico.springcloud.msvc.usuario.application.usecases;

import org.academico.springcloud.msvc.usuario.domain.model.Usuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.CrearUsuarioUseCase;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrearUsuarioUseCaseImpl implements CrearUsuarioUseCase {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public CrearUsuarioUseCaseImpl(UsuarioRepositoryPort usuarioRepositoryPort) {
        this.usuarioRepositoryPort = usuarioRepositoryPort;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("El usuario no puede ser nulo");
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
        return usuarioRepositoryPort.save(usuario);
    }
}