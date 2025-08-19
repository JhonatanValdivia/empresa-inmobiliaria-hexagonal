package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.enums.TipoUsuario;
import org.inmobiliaria.springcloud.msvc.propiedades.domain.ports.out.ExternalServicePort;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.pojos.NombreCompleto;
import org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters.pojos.Usuario;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExternalServiceAdapter implements ExternalServicePort {
    private final UsuarioClientRest usuarioClientRest;

    public ExternalServiceAdapter(UsuarioClientRest usuarioClientRest) {
        this.usuarioClientRest = usuarioClientRest;
    }

    @Override
    public Optional<UsuarioDetails> getUsuarioDetails(Long usuarioId) {
        try {

            Usuario usuario = usuarioClientRest.detalle(usuarioId);
            if (usuario == null) {
                return Optional.empty();
            }
            String nombre;
            NombreCompleto nc = usuario.getNombreCompleto();
            if (nc != null) {
                nombre = nc.toString();
            } else {
                nombre = null;
            }
            TipoUsuario tipo = usuario.getTipoUsuario();

            UsuarioDetails details = new UsuarioDetails(usuario.getId(), nombre, tipo);
            return Optional.of(details);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
