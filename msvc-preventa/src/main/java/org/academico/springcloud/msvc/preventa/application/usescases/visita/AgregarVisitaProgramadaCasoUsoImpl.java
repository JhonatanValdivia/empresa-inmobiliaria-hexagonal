package org.academico.springcloud.msvc.preventa.application.usescases.visita;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.VisitaProgramada;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.AgregarVisitaProgramadaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AgregarVisitaProgramadaCasoUsoImpl implements AgregarVisitaProgramadaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AgregarVisitaProgramadaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> agregarVisitaProgramada(Long preventaId, VisitaProgramada visita) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            try {
                visita.setId(null); // Asegura que sea una nueva inserción
                preventa.addVisitaProgramada(visita); // Lógica de agregado
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Error al agregar visita programada: " + e.getMessage(), e);
            }
            return preventaRepositorioPort.guardar(preventa); // Guarda el agregado
        });
    }
}