package org.academico.springcloud.msvc.preventa.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.academico.springcloud.msvc.preventa.domain.models.enums.EstadoVisita; // Usamos el enum del dominio

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "visitas_programadas")
public class VisitaProgramadaEntidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    private EstadoVisita estadoVisita; // Enum del dominio

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preventa_id", nullable = false) // Clave foránea a Preventa
    @JsonBackReference // Lado inverso de la relación
    private PreventaEntidad preventa; // Referencia a la entidad padre

    public VisitaProgramadaEntidad() {}

    public VisitaProgramadaEntidad(Long id, LocalDate fecha, EstadoVisita estadoVisita, PreventaEntidad preventa) {
        this.id = id;
        this.fecha = fecha;
        this.estadoVisita = estadoVisita;
        this.preventa = preventa;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public EstadoVisita getEstadoVisita() { return estadoVisita; }
    public void setEstadoVisita(EstadoVisita estadoVisita) { this.estadoVisita = estadoVisita; }
    public PreventaEntidad getPreventa() { return preventa; }
    public void setPreventa(PreventaEntidad preventa) { this.preventa = preventa; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisitaProgramadaEntidad that = (VisitaProgramadaEntidad) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}