package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.RecuperarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PropiedadPort;
import org.academico.springcloud.msvc.preventa.domain.ports.out.UsuarioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

public class RecuperarPreventaCasoUsoImpl implements RecuperarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;
    private final PropiedadPort propiedadPort;
    private final UsuarioPort usuarioPort;


    @Autowired
    public RecuperarPreventaCasoUsoImpl(
            PreventaRepositorioPort preventaRepositorioPort,
            PropiedadPort propiedadPort,
            UsuarioPort usuarioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
        this.propiedadPort = propiedadPort;
        this.usuarioPort = usuarioPort;
    }

    @Override
    public Optional<Preventa> preventaPorId(Long id) {
        Optional<Preventa> preventaOpt = preventaRepositorioPort.porId(id);
        if (preventaOpt.isPresent()) {
            Preventa preventa = preventaOpt.get();
            if (preventa.getIdPropiedad() != null) {
                propiedadPort.obtenerPorId(preventa.getIdPropiedad())
                        .ifPresent(propiedadDetallada -> {
                            preventa.setPropiedad(propiedadDetallada);
                        });
            }
            if (preventa.getUsuarioAgenteId() != null) {
                usuarioPort.obtenerUsuario(preventa.getUsuarioAgenteId())
                        .ifPresent(preventa::setAgente);
            }
            if (preventa.getUsuarioClienteId() != null) {
                usuarioPort.obtenerUsuario(preventa.getUsuarioClienteId())
                        .ifPresent(preventa::setCliente);
            }
        }
        return preventaOpt;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Preventa> obtenerTodasPreventas() {
        return preventaRepositorioPort.listar();
    }
}