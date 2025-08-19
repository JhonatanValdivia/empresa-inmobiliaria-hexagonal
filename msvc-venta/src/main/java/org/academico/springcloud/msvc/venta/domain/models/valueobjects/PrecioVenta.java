package org.academico.springcloud.msvc.venta.domain.models.valueobjects;

import java.math.BigDecimal;
import java.util.Objects;

public class PrecioVenta {
    private final BigDecimal precioVenta;
    private final String moneda;

    protected PrecioVenta() {
        this.precioVenta = BigDecimal.ZERO;
        this.moneda = "";
    }

    public PrecioVenta(BigDecimal precioVenta, String moneda) {
        if (precioVenta == null || precioVenta.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio de venta debe ser mayor que cero.");
        }
        if (moneda == null || moneda.isBlank()) {
            throw new IllegalArgumentException("La moneda es obligatoria.");
        }
        // Puedes añadir validación de formato de moneda (ej. regex o una lista predefinida)
        // Por simplicidad, aquí solo se valida que no esté en blanco.

        this.precioVenta = precioVenta;
        this.moneda = moneda;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecioVenta that = (PrecioVenta) o;
        return Objects.equals(precioVenta, that.precioVenta) &&
                Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(precioVenta, moneda);
    }

    @Override
    public String toString() {
        return precioVenta + " " + moneda;
    }
}