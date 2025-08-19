package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.CrearPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class CrearPreventaCasoUsoImpl implements CrearPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public CrearPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Preventa crearPreventa(Preventa preventa) {
        if (preventa.getFechaInicio() == null) {
            throw new IllegalArgumentException("La fecha de inicio es un campo obligatorio para crear una preventa.");
        }
        if (preventa.getUsuarioAgenteId() == null || preventa.getUsuarioClienteId() == null) {
            throw new IllegalArgumentException("El agente y el cliente deben estar asignados para crear una preventa.");
        }
        if (preventa.getIdPropiedad() == null) {
            throw new IllegalArgumentException("Se debe asignar una propiedad para crear una preventa.");
        }
        return preventaRepositorioPort.guardar(preventa);
    }
}