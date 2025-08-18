package org.academico.springcloud.msvc.preventa.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.academico.springcloud.msvc.preventa.domain.models.enums.MetodoPago; // Usamos el enum del dominio

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "propuestas_pago")
public class PropuestaPagoEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal monto;
    private LocalDate fecha;
    private Integer cuotas;

    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago; // Enum del dominio

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preventa_id", nullable = false) // Clave foránea a Preventa
    @JsonBackReference // Lado inverso de la relación
    private PreventaEntidad preventa; // Referencia a la entidad padre

    public PropuestaPagoEntidad() {}

    public PropuestaPagoEntidad(Long id, BigDecimal monto, LocalDate fecha, Integer cuotas, MetodoPago metodoPago, PreventaEntidad preventa) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.cuotas = cuotas;
        this.metodoPago = metodoPago;
        this.preventa = preventa;
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
    public PreventaEntidad getPreventa() { return preventa; }
    public void setPreventa(PreventaEntidad preventa) { this.preventa = preventa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PropuestaPagoEntidad that = (PropuestaPagoEntidad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}