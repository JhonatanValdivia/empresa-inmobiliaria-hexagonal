package org.academico.springcloud.msvc.preventa.application.usescases.preventa;

import org.academico.springcloud.msvc.preventa.domain.models.Usuario;
import org.academico.springcloud.msvc.preventa.domain.models.domainentities.Preventa;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.AsociarUsuariosPreventa;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.academico.springcloud.msvc.preventa.domain.ports.out.UsuarioPort;

public class AsociarUsuarioPreventaImpl implements AsociarUsuariosPreventa {
    private final PreventaRepositorioPort preventaRepositorioPort;
    private final UsuarioPort usuarioPort;

    public AsociarUsuarioPreventaImpl(PreventaRepositorioPort preventaRepositorioPort, UsuarioPort usuarioPort) {
        this.preventaRepositorioPort = preventaRepositorioPort;
        this.usuarioPort = usuarioPort;
    }

    @Override
    public Preventa asociarUsuariosPreventa(Long idPreventa, Long idAgente, Long idCliente) {
        // Obtener usuarios del microservicio a través del puerto
        Usuario agente = usuarioPort.obtenerUsuario(idAgente);
        Usuario cliente = usuarioPort.obtenerUsuario(idCliente);

        // Validar tipo de usuario con Enum de dominio
        if (agente.getTipoUsuario() != Usuario.TipoUsuario.AGENTE) {
            throw new IllegalArgumentException("El usuario con id " + idAgente + " no es un agente válido");
        }
        if (cliente.getTipoUsuario() != Usuario.TipoUsuario.CLIENTE) {
            throw new IllegalArgumentException("El usuario con id " + idCliente + " no es un cliente válido");
        }

        Preventa preventa = preventaRepositorioPort.porId(idPreventa)
                .orElseThrow(() -> new IllegalArgumentException("Preventa no encontrada con id: " + idPreventa));

        preventa.asociarUsuarios(idAgente, idCliente);

        return preventaRepositorioPort.guardar(preventa);
    }
}
