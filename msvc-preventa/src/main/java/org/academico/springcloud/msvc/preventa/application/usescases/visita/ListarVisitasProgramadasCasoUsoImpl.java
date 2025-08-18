package org.academico.springcloud.msvc.preventa.application.usescases.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.ListarVisitasProgramadasCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class ListarVisitasProgramadasCasoUsoImpl implements ListarVisitasProgramadasCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public ListarVisitasProgramadasCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<VisitaProgramada>> listarVisitasProgramadasPorPreventa(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId)
                .map(Preventa::getVisitasProgramadas); // Obtiene la lista de visitas del agregado
    }
}