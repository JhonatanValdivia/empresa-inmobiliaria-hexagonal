package org.academico.springcloud.msvc.venta.domain.models.valueobjects;

/**
 * Value Object para representar la información mínima de una Preventa
 * que es relevante para el dominio de Venta.
 * En Java 17+, un record es una forma concisa de definir una clase inmutable de solo datos.
 */
public record PreventaInfo(Long id, String estado) {
    // Los records generan automáticamente el constructor, getters, equals, hashCode y toString.
    // Si necesitas más campos de Preventa (ej. fechaInicio), agrégalos aquí.
}