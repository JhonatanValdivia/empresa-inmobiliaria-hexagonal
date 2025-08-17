package org.academico.springcloud.msvc.usuario.domain.ports.in;

import org.academico.springcloud.msvc.usuario.domain.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface ObtenerUsuarioUseCase {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerPorId(Long id);
    List<Usuario> obtenerPorIds(List<Long> ids); // Nuevo: Listar por lista de IDs
}