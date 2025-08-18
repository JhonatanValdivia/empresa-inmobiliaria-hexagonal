package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.AprobarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

public class AprobarPreventaCasoUsoImpl implements AprobarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AprobarPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> aprobarPreventa(Long preventaId) {
        return preventaRepositorioPort.porId(preventaId).map(preventa -> {
            try {
                preventa.aprobarPreventa(); // Lógica de dominio para aprobar
            } catch (IllegalStateException e) {
                throw new RuntimeException("Error al aprobar preventa: " + e.getMessage(), e); // Envuelve excepción de dominio
            }
            return preventaRepositorioPort.guardar(preventa); // Guarda el agregado
        });
    }
}