package org.academico.springcloud.msvc.usuario.domain.ports.in;

import org.academico.springcloud.msvc.usuario.domain.model.Usuario;
import org.academico.springcloud.msvc.usuario.domain.enums.TipoUsuario;

import java.util.List;

public interface ObtenerUsuariosPorTipoUseCase {
    List<Usuario> obtenerPorTipo(TipoUsuario tipoUsuario);
}