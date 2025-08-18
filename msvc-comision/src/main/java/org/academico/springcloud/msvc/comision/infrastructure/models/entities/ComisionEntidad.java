package org.academico.springcloud.msvc.comision.infrastructure.models.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.comision.domain.models.enums.EstadoComision;
import org.academico.springcloud.msvc.comision.domain.models.enums.TipoComision;
import org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects.FechaPagoComisionVO;
import org.academico.springcloud.msvc.comision.infrastructure.models.valueObjects.MontoComisionVO;

@Entity
@Table(name = "comisiones")
public class ComisionEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private EstadoComision estadoComision;

    @Enumerated(EnumType.STRING)
    private TipoComision tipoComision;

    @Embedded
    @AttributeOverride(name = "montoComision", column = @Column(name = "monto_comision"))
    private MontoComisionVO montoComision;

    @Embedded
    private FechaPagoComisionVO fechaPagoComision;

    @Column(name = "venta_id", unique = true)
    private Long ventaId;

    @Column(name = "agente_id")
    private Long agenteId;

    /*@Transient
    private Venta venta;

    @Transient
    private Usuario usuario;*/

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public EstadoComision getEstadoComision() { return estadoComision; }
    public void setEstadoComision(EstadoComision estadoComision) { this.estadoComision = estadoComision; }
    public TipoComision getTipoComision() { return tipoComision; }
    public void setTipoComision(TipoComision tipoComision) { this.tipoComision = tipoComision; }
    public MontoComisionVO getMontoComision() { return montoComision; }
    public void setMontoComision(MontoComisionVO montoComision) { this.montoComision = montoComision; }
    public FechaPagoComisionVO getFechaPagoComision() { return fechaPagoComision; }
    public void setFechaPagoComision(FechaPagoComisionVO fechaPagoComision) { this.fechaPagoComision = fechaPagoComision; }
    public Long getVentaId() { return ventaId; }
    public void setVentaId(Long ventaId) { this.ventaId = ventaId; }
    public Long getAgenteId() { return agenteId; }
    public void setAgenteId(Long agenteId) { this.agenteId = agenteId; }
   /* public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }*/
}
