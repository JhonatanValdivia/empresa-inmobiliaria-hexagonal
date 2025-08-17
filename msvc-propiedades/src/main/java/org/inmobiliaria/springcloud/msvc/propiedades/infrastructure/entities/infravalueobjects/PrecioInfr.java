package org.inmobiliaria.springcloud.msvc.propiedades.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.Objects;
@Embeddable
public class PrecioInfr {
    private BigDecimal monto;
    private String moneda;

    public PrecioInfr() {}

    public PrecioInfr(BigDecimal monto, String moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto debe ser mayor a cero");
        }
        if (moneda == null || moneda.isEmpty()) {
            throw new IllegalArgumentException("Moneda no puede ser vacía");
        }

        this.monto = monto;
        this.moneda = moneda;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public String toString() {
        return "Precio{" +
                "monto=" + monto +
                ", moneda='" + moneda + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PrecioInfr)) return false;
        PrecioInfr other = (PrecioInfr) obj;
        return monto.equals(other.monto) && moneda.equals(other.moneda);
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, moneda);
    }
}
