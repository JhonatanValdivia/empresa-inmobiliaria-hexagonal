package org.academico.springcloud.msvc.preventa.domain.models.domainentities;

import org.academico.springcloud.msvc.preventa.domain.models.enums.TipoContrato;

import java.time.LocalDate;
import java.util.Objects;

public class ContratoVenta {
    private Long id;
    private TipoContrato tipoContrato;
    private LocalDate fechaFirma;
    private String estado; // "Firmado", "Anulado", etc.
    // La referencia a Preventa se gestionará por el agregado, no se pone aquí para evitar acoplamiento directo

    public ContratoVenta() {
        this.estado = "Pendiente"; // Estado inicial por defecto
    }

    public ContratoVenta(Long id, TipoContrato tipoContrato, LocalDate fechaFirma, String estado) {
        if (tipoContrato == null) {
            throw new IllegalArgumentException("El tipo de contrato es obligatorio.");
        }
        if (fechaFirma == null) {
            throw new IllegalArgumentException("La fecha de firma es obligatoria.");
        }
        if (estado == null || estado.isBlank()) {
            throw new IllegalArgumentException("El estado del contrato es obligatorio.");
        }
        this.id = id;
        this.tipoContrato = tipoContrato;
        this.fechaFirma = fechaFirma;
        this.estado = estado;
    }

    // Lógica de dominio
    public void anularContrato() {
        if ("Firmado".equals(this.estado)) {
            this.estado = "Anulado";
            System.out.println("Contrato anulado."); // Ejemplo de efecto secundario de dominio
        } else {
            throw new IllegalStateException("Solo se pueden anular contratos firmados. Estado actual: " + this.estado);
        }
    }

    public void cambiarEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new IllegalArgumentException("El nuevo estado no puede ser nulo o vacío.");
        }
        this.estado = nuevoEstado;
        System.out.println("Estado del contrato cambiado a: " + nuevoEstado); // Ejemplo de efecto secundario de dominio
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public LocalDate getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(LocalDate fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContratoVenta that = (ContratoVenta) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}