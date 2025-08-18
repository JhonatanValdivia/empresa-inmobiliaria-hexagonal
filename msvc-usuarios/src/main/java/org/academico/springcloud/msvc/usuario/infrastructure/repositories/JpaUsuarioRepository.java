package org.academico.springcloud.msvc.usuario.infrastructure.repositories;

import org.academico.springcloud.msvc.usuario.domain.models.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.infrastructure.models.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface JpaUsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByCorreoElectronico(String email);
    List<UsuarioEntity> findAllByIdIn(List<Long> ids);
    long count();
    boolean existsById(Long id);
    List<UsuarioEntity> findByTipoUsuario(TipoUsuario tipoUsuario);
}