package org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class PrecioVentaInfr {
    private BigDecimal precioVenta;
    private String moneda;

    public PrecioVentaInfr() {}

    public PrecioVentaInfr(BigDecimal precioVenta, String moneda) {
        this.precioVenta = precioVenta;
        this.moneda = moneda;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecioVentaInfr that = (PrecioVentaInfr) o;
        return Objects.equals(precioVenta, that.precioVenta) &&
                Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precioVenta, moneda);
    }
}