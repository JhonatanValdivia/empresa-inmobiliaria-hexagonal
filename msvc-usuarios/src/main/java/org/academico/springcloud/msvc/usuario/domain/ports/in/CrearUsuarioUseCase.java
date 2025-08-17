package org.academico.springcloud.msvc.usuario.domain.ports.in;

import org.academico.springcloud.msvc.usuario.domain.model.Usuario;

public interface CrearUsuarioUseCase {
    Usuario crearUsuario(Usuario usuario);
}