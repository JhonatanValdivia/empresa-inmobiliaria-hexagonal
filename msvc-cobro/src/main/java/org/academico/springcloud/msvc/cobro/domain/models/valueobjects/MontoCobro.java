package org.academico.springcloud.msvc.cobro.domain.models.valueobjects;

import java.math.BigDecimal;
import java.util.Objects;

public class MontoCobro {

    private final BigDecimal monto;
    private final String moneda;

    // Constructor sin argumentos (puede ser requerido por Spring si se usa en alguna parte)
    protected MontoCobro() {
        this.monto = BigDecimal.ZERO;
        this.moneda = "";
    }

    public MontoCobro(BigDecimal monto, String moneda) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0 || monto.compareTo(new BigDecimal("1000000")) > 0) {
            throw new IllegalArgumentException("El monto debe estar entre 0.01 y 1,000,000.");
        }
        if (moneda == null || moneda.trim().isEmpty()){
            throw new IllegalArgumentException("La moneda es obligatoria.");
        }

        // Validación básica de códigos ISO 4217 (puedes expandir esta lista)
        String[] validCurrencies = {"PEN", "USD", "EUR"};
        boolean isValidCurrency = false;
        for (String curr : validCurrencies) {
            if (curr.equals(moneda.trim().toUpperCase())) {
                isValidCurrency = true;
                break;
            }
        }
        if (!isValidCurrency) {
            throw new IllegalArgumentException("Moneda no válida. Use PEN, USD o EUR.");
        }

        this.monto = monto;
        this.moneda = moneda.trim().toUpperCase();
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    @Override
    public String toString() {
        return String.format("%s %s", monto, moneda);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MontoCobro that = (MontoCobro) o;
        return Objects.equals(monto, that.monto) && Objects.equals(moneda, that.moneda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(monto, moneda);
    }
}