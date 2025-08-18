package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import org.academico.springcloud.msvc.preventa.domain.models.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class PropuestaPago {
    private Long id;
    private BigDecimal monto;
    private LocalDate fecha;
    private Integer cuotas;
    private MetodoPago metodoPago;
    // Referencia a Preventa gestionada por el agregado

    public PropuestaPago() {} // Constructor público vacío para Jackson

    public PropuestaPago(Long id, BigDecimal monto, LocalDate fecha, Integer cuotas, MetodoPago metodoPago) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto de la propuesta de pago debe ser mayor que cero.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de la propuesta de pago es obligatoria.");
        }
        if (cuotas == null || cuotas <= 0) {
            throw new IllegalArgumentException("El número de cuotas debe ser mayor que cero.");
        }
        if (metodoPago == null) {
            throw new IllegalArgumentException("El método de pago de la propuesta es obligatorio.");
        }
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.cuotas = cuotas;
        this.metodoPago = metodoPago;
    }

    // Lógica de dominio
    public void aceptarPropuesta() {
        System.out.println("Propuesta de pago aceptada.");
        // Podría cambiar un estado interno si PropuestaPago lo tuviera
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Integer getCuotas() { return cuotas; }
    public void setCuotas(Integer cuotas) { this.cuotas = cuotas; }
    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropuestaPago that = (PropuestaPago) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}