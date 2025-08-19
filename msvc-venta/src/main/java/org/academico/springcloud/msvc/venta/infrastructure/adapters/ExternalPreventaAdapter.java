package org.academico.springcloud.msvc.venta.infrastructure.adapters;

import org.academico.springcloud.msvc.venta.domain.ports.out.PreventaPort;
import org.academico.springcloud.msvc.venta.domain.models.valueobjects.PreventaInfo;
import org.academico.springcloud.msvc.venta.infrastructure.clients.PreventaClientRest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExternalPreventaAdapter implements PreventaPort {

    private final PreventaClientRest preventaClientRest;

    public ExternalPreventaAdapter(PreventaClientRest preventaClientRest) {
        this.preventaClientRest = preventaClientRest;
    }

    @Override
    public Optional<PreventaInfo> obtenerInfoPreventa(Long idPreventa) {
        // Llama al Feign Client y mapea el Pojo recibido al Value Object de dominio
        return preventaClientRest.obtenerPreventa(idPreventa)
                .map(pojo -> new PreventaInfo(pojo.getId(), pojo.getEstado())); // Mapea a tu VO de dominio
    }
}