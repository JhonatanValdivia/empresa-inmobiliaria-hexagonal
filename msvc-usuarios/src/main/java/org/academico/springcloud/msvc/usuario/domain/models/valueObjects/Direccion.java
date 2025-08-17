package org.academico.springcloud.msvc.usuario.domain.models.valueObjects;

import java.util.Objects;

public class Direccion {
    private String ubigeo;
    private String ciudad;
    private String direccion;

    protected Direccion() {}

    public Direccion(String ubigeo, String ciudad, String direccion) {
        if (ciudad == null || ciudad.trim().isEmpty()) {
            throw new IllegalArgumentException("La ciudad es obligatoria.");
        }
        if (ciudad.length() > 100) {
            throw new IllegalArgumentException("La ciudad no puede tener más de 100 caracteres.");
        }
        if (direccion == null || direccion.trim().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria.");
        }
        if (direccion.length() > 255) {
            throw new IllegalArgumentException("La dirección no puede tener más de 255 caracteres.");
        }

        this.ubigeo = (ubigeo != null) ? ubigeo.trim() : null;
        this.ciudad = ciudad.trim();
        this.direccion = direccion.trim().replaceAll("\\s+", " ");

        if (this.ubigeo != null && !this.ubigeo.matches("\\d{6}")) {
            throw new IllegalArgumentException("El ubigeo debe tener exactamente 6 dígitos numéricos.");
        }
    }

    public String getUbigeo() { return ubigeo; }
    public String getCiudad() { return ciudad; }
    public String getDireccion() { return direccion; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direccion)) return false;
        Direccion that = (Direccion) o;
        return Objects.equals(ubigeo, that.ubigeo) && ciudad.equals(that.ciudad) && direccion.equals(that.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ubigeo, ciudad, direccion);
    }

    @Override
    public String toString() {
        return (ubigeo != null ? ubigeo + ", " : "") + ciudad + ": " + direccion;
    }
}