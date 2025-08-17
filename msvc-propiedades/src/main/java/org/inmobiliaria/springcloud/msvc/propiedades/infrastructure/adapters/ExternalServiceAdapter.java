package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.adapters;

import org.inmobiliaria.springcloud.msvc.propiedades.domain.models.domainentities.UsuarioDetails;
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
    public Optional<UsuarioDetails> getUsuarioDetails(Long propiedadId) {
        try {

            Usuario usuario = usuarioClientRest.detalle(propiedadId);
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

            UsuarioDetails details = new UsuarioDetails(usuario.getId(), nombre);
            return Optional.of(details);

        } catch (Exception ex) {
            return Optional.empty();
        }
    }
}
