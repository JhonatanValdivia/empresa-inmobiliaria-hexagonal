package org.academico.springcloud.msvc.preventa.application.usescases.propuesta;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.PropuestaPago;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.ListarPropuestasPagoCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class ListarPropuestasPagoCasoUsoImpl implements ListarPropuestasPagoCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ListarPropuestasPagoCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<PropuestaPago>> listarPropuestasPagoPorPreventa(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId)
                .map(Preventa::getPropuestasPago); // Obtiene la lista de propuestas del agregado
    }
}