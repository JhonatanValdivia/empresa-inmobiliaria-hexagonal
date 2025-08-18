package org.academico.springcloud.msvc.norma.domain.models.enums;

public enum TipoNorma {
    SEGURIDAD("Seguridad"),
    AMBIENTAL("Ambiental"),
    CONSTRUCCION("Construcción");

    private final String descripcion;

    TipoNorma(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}