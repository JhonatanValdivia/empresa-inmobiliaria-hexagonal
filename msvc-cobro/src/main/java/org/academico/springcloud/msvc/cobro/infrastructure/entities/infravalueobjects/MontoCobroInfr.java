package org.academico.springcloud.msvc.cobro.infrastructure.entities.infravalueobjects;

import jakarta.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class MontoCobroInfr {

    private BigDecimal monto;
    private String moneda;

    public MontoCobroInfr() {}

    public MontoCobroInfr(BigDecimal monto, String moneda) {
        this.monto = monto;
        this.moneda = moneda;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return String.format("%s %s", monto, moneda);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MontoCobroInfr that = (MontoCobroInfr) o;
        return Objects.equals(monto, that.monto) && Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, moneda);
    }
}