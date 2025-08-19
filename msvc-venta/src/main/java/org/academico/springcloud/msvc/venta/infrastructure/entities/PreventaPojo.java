package org.academico.springcloud.msvc.venta.infrastructure.entities;

import java.time.LocalDate;

/**
 * POJO simple para recibir la información de Preventa del microservicio msvc-preventa
 * a través de Feign Client. No es una entidad de persistencia local.
 */
public class PreventaPojo {
    private Long id;
    private String estado; // Necesario para la validación de estado "APROBADA"
    private LocalDate fechaInicio; // Ejemplo: si necesitas la fecha de inicio de la Preventa

    // Constructor vacío para deserialización JSON
    public PreventaPojo() {}

    // Constructor con todos los campos (útil para pruebas o instanciación manual)
    public PreventaPojo(Long id, String estado, LocalDate fechaInicio) {
        this.id = id;
        this.estado = estado;
        this.fechaInicio = fechaInicio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
}