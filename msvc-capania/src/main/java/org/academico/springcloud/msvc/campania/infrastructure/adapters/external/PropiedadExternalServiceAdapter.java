package org.academico.springcloud.msvc.campania.infrastructure.adapters.external;

import org.academico.springcloud.msvc.campania.domain.models.Propiedad;
import org.academico.springcloud.msvc.campania.domain.ports.out.PropiedadExternalServicePort;
import org.academico.springcloud.msvc.campania.infrastructure.clients.PropiedadClientRest;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class PropiedadExternalServiceAdapter implements PropiedadExternalServicePort {

    private final PropiedadClientRest clientRest;

    public PropiedadExternalServiceAdapter(PropiedadClientRest clientRest) {
        this.clientRest = clientRest;
    }

    @Override
    public Propiedad findById(Long id) {
        try {
            return clientRest.detalle(id);
        } catch (Exception e) {
            // Manejo básico, ajusta según necesidad
            return null;
        }
    }
}
