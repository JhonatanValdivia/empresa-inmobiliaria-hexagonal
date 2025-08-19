package org.academico.springcloud.msvc.venta.domain.models.domainentities;

import org.academico.springcloud.msvc.venta.domain.models.enums.EstadoDetalle;
import org.academico.springcloud.msvc.venta.domain.models.enums.MetodoPago;
import org.academico.springcloud.msvc.venta.domain.models.valueobjects.CronogramaPago;

import java.util.Objects;

public class DetalleVenta {

    private Long id;
    private CronogramaPago cronogramaPago;
    private MetodoPago metodoPago;
    private EstadoDetalle estadoDetalle;

    public DetalleVenta() {
        this.estadoDetalle = EstadoDetalle.PENDIENTE; // Valor por defecto
    }

    public DetalleVenta(Long id, CronogramaPago cronogramaPago, MetodoPago metodoPago, EstadoDetalle estadoDetalle) {
        if (cronogramaPago == null) {
            throw new IllegalArgumentException("El cronograma de pago es obligatorio para el detalle de venta.");
        }
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago es obligatorio para el detalle de venta.");
        }

        this.id = id;
        this.cronogramaPago = cronogramaPago;
        this.metodoPago = metodoPago;
        this.estadoDetalle = (estadoDetalle != null) ? estadoDetalle : EstadoDetalle.PENDIENTE;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CronogramaPago getCronogramaPago() {
        return cronogramaPago;
    }

    public void setCronogramaPago(CronogramaPago cronogramaPago) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DetalleVenta that = (DetalleVenta) o;
        return Objects.equals(id, that.id); // La igualdad por ID es común para entidades de dominio
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}