package org.academico.springcloud.msvc.usuario.infrastructure.models.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.usuario.domain.models.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.CorreoElectronicoVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.DireccionVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.NombreCompletoVO;
import org.academico.springcloud.msvc.usuario.infrastructure.models.valueObjects.TelefonoVO;

@Entity
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private NombreCompletoVO nombreCompleto;

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;

    @Embedded
    private TelefonoVO telefono;

    @Embedded
    private CorreoElectronicoVO correoElectronico;

    @Embedded
    private DireccionVO direccion;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public NombreCompletoVO getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(NombreCompletoVO nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public TipoUsuario getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(TipoUsuario tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public TelefonoVO getTelefono() { return telefono; }
    public void setTelefono(TelefonoVO telefono) { this.telefono = telefono; }

    public CorreoElectronicoVO getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(CorreoElectronicoVO correoElectronico) { this.correoElectronico = correoElectronico; }

    public DireccionVO getDireccion() { return direccion; }
    public void setDireccion(DireccionVO direccion) { this.direccion = direccion; }
}