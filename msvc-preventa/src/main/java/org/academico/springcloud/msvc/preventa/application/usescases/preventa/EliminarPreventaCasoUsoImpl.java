package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.EliminarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class EliminarPreventaCasoUsoImpl implements EliminarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public EliminarPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public void eliminarPreventa(Long id) {
        // Puedes añadir validación de existencia aquí si quieres manejar un caso de "no encontrado" antes de eliminar
        preventaRepositorioPort.eliminarPorId(id);
    }
}