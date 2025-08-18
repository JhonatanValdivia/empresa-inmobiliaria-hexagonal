package org.academico.springcloud.msvc.preventa.application.usescases.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.AgregarPropuestaPagoCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AgregarPropuestaPagoCasoUsoImpl implements AgregarPropuestaPagoCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AgregarPropuestaPagoCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> agregarPropuestaPago(Long preventaId, PropuestaPago propuesta) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            try {
                propuesta.setId(null); // Asegura que sea una nueva inserción
                preventa.addPropuestaPago(propuesta); // Lógica de agregado
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Error al agregar propuesta de pago: " + e.getMessage(), e);
            }
            return preventaRepositorioPort.guardar(preventa); // Guarda el agregado
        });
    }
}