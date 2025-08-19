package org.academico.springcloud.msvc.venta.infrastructure.entities;

import jakarta.persistence.*;
import org.academico.springcloud.msvc.venta.domain.models.enums.EstadoDetalle;
import org.academico.springcloud.msvc.venta.domain.models.enums.MetodoPago;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.CronogramaPagoInfr;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "detalle_ventas")
public class DetalleVentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CronogramaPagoInfr cronogramaPago;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;

    @Enumerated(EnumType.STRING)
    private EstadoDetalle estadoDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venta_id", nullable = false)
    @JsonIgnore
    private VentaEntity venta;

    public DetalleVentaEntity() {}

    public DetalleVentaEntity(Long id, CronogramaPagoInfr cronogramaPago, MetodoPago metodoPago, EstadoDetalle estadoDetalle, VentaEntity venta) {
        this.id = id;
        this.cronogramaPago = cronogramaPago;
        this.metodoPago = metodoPago;
        this.estadoDetalle = estadoDetalle;
        this.venta = venta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CronogramaPagoInfr getCronogramaPago() {
        return cronogramaPago;
    }

    public void setCronogramaPago(CronogramaPagoInfr cronogramaPago) {
        this.cronogramaPago = cronogramaPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public EstadoDetalle getEstadoDetalle() {
        return estadoDetalle;
    }

    public void setEstadoDetalle(EstadoDetalle estadoDetalle) {
        this.estadoDetalle = estadoDetalle;
    }

    public VentaEntity getVenta() {
        return venta;
    }

    public void setVenta(VentaEntity venta) {
        this.venta = venta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVentaEntity that = (DetalleVentaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}