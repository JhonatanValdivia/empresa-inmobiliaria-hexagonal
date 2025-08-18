package org.academico.springcloud.msvc.preventa.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.academico.springcloud.msvc.preventa.domain.models.enums.TipoContrato; // Usamos el enum del dominio

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "contratos_venta") // Nombre de tabla corregido
public class ContratoVentaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoContrato tipoContrato; // Enum del dominio
    private LocalDate fechaFirma;
    private String estado; // Estado como String, como en tu original

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preventa_id", unique = true, nullable = false) // 'nullable = false' para asegurar la FK
    @JsonBackReference // Evita ciclos de serialización JSON en relaciones bidireccionales
    private PreventaEntidad preventa; // Referencia a la entidad padre

    public ContratoVentaEntidad() {}

    public ContratoVentaEntidad(Long id, TipoContrato tipoContrato, LocalDate fechaFirma, String estado, PreventaEntidad preventa) {
        this.id = id;
        this.tipoContrato = tipoContrato;
        this.fechaFirma = fechaFirma;
        this.estado = estado;
        this.preventa = preventa;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TipoContrato getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(TipoContrato tipoContrato) { this.tipoContrato = tipoContrato; }
    public LocalDate getFechaFirma() { return fechaFirma; }
    public void setFechaFirma(LocalDate fechaFirma) { this.fechaFirma = fechaFirma; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public PreventaEntidad getPreventa() { return preventa; }
    public void setPreventa(PreventaEntidad preventa) { this.preventa = preventa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoVentaEntidad that = (ContratoVentaEntidad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}