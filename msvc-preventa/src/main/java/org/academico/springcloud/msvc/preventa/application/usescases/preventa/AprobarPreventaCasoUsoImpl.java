package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoPreventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.AprobarPreventaCasoUso;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AprobarPreventaCasoUsoImpl implements AprobarPreventaCasoUso {

    private final PreventaRepositorioPort preventaRepositorioPort;

    @Autowired
    public AprobarPreventaCasoUsoImpl(PreventaRepositorioPort preventaRepositorioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
    }

    @Override
    @Transactional
    public Optional<Preventa> aprobarPreventa(Long preventaId) {
        Preventa preventaAprobar = preventaRepositorioPort.porId(preventaId)
                .orElseThrow(() -> new IllegalStateException("La preventa con ID " + preventaId + " no fue encontrada."));

        if (preventaAprobar.getEstado() != EstadoPreventa.EN_EVALUACION) {
            throw new IllegalStateException("La preventa con ID " + preventaId + " ya se encuentra en estado " + preventaAprobar.getEstado() + " y no puede ser aprobada.");
        }

        Long idPropiedad = preventaAprobar.getIdPropiedad();
        Long idCliente = preventaAprobar.getUsuarioClienteId();

        // Verificar que el cliente no tenga ya otra preventa aprobada.
        if (preventaRepositorioPort.existePorUsuarioClienteIdYEstado(idCliente, EstadoPreventa.APROBADA)) {
            throw new IllegalStateException("Operación cancelada: El cliente con ID " + idCliente + " ya tiene otra preventa aprobada.");
        }

        // Buscar y eliminar otras preventas EN_EVALUACION para la misma propiedad.
        List<Preventa> otrasPreventas = preventaRepositorioPort.buscarPorIdPropiedadYEstado(idPropiedad, EstadoPreventa.EN_EVALUACION);

        List<Long> idsParaEliminar = otrasPreventas.stream()
                .filter(p -> !p.getId().equals(preventaId)) // Excluimos la que estamos aprobando
                .map(Preventa::getId)
                .collect(Collectors.toList());

        if (!idsParaEliminar.isEmpty()) {
            preventaRepositorioPort.eliminarPorIds(idsParaEliminar);
        }

        // 5. Aprobar la preventa actual
        preventaAprobar.aprobarPreventa();

        // 6. Guardar y devolver la preventa ya aprobada
        return Optional.of(preventaRepositorioPort.guardar(preventaAprobar));
    }
}