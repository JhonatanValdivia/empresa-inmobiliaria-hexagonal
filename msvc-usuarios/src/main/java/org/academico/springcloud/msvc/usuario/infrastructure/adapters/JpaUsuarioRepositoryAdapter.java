package org.academico.springcloud.msvc.usuario.infrastructure.adapters;

import org.academico.springcloud.msvc.usuario.domain.models.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.ports.out.UsuarioRepositoryPort;
import org.academico.springcloud.msvc.usuario.infrastructure.models.entities.UsuarioEntity;
import org.academico.springcloud.msvc.usuario.infrastructure.mappers.UsuarioMapper;
import org.academico.springcloud.msvc.usuario.infrastructure.repositories.JpaUsuarioRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class JpaUsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final JpaUsuarioRepository jpaRepository;
    private final UsuarioMapper usuarioMapper;

    public JpaUsuarioRepositoryAdapter(JpaUsuarioRepository jpaRepository, UsuarioMapper usuarioMapper) {
        this.jpaRepository = jpaRepository;
        this.usuarioMapper = usuarioMapper;
    }

    @Override
    public List<Usuario> findAll() {
        return StreamSupport.stream(jpaRepository.findAll().spliterator(), false)
                .map(usuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return jpaRepository.findById(id).map(usuarioMapper::toDomain);
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioMapper.toEntity(usuario);
        return usuarioMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return jpaRepository.findByCorreoElectronico(email).map(usuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> findAllById(List<Long> ids) {
        return jpaRepository.findAllByIdIn(ids).stream()
                .map(usuarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Override
    public boolean existsById(Long id) {
        return jpaRepository.existsById(id);
    }

    @Override
    public List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario) {
        return jpaRepository.findByTipoUsuario(tipoUsuario).stream()
                .map(usuarioMapper::toDomain)
                .collect(Collectors.toList());
    }
}