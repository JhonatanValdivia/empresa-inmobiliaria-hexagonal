package org.academico.springcloud.msvc.usuario.domain.models.valueObjects;

import java.util.Objects;

public class NombreCompleto {
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;

    protected NombreCompleto() {}

    public NombreCompleto(String primerNombre, String segundoNombre, String primerApellido, String segundoApellido) {
        if (primerNombre == null || primerNombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El primer nombre es obligatorio.");
        }
        if (primerApellido == null || primerApellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El primer apellido es obligatorio.");
        }

        this.primerNombre = primerNombre.trim().replaceAll("\\s{2,}", " ");
        this.segundoNombre = (segundoNombre != null) ? segundoNombre.trim().replaceAll("\\s{2,}", " ") : "";
        this.primerApellido = primerApellido.trim().replaceAll("\\s{2,}", " ");
        this.segundoApellido = (segundoApellido != null) ? segundoApellido.trim().replaceAll("\\s{2,}", " ") : "";

        if (primerNombre.length() > 50 || (segundoNombre != null && segundoNombre.length() > 50) ||
                primerApellido.length() > 50 || (segundoApellido != null && segundoApellido.length() > 50)) {
            throw new IllegalArgumentException("Cada parte del nombre debe tener como máximo 50 caracteres.");
        }

        String regex = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü'\\- ]+$";
        if (!primerNombre.matches(regex) || (!segundoNombre.isEmpty() && !segundoNombre.matches(regex)) ||
                !primerApellido.matches(regex) || (!segundoApellido.isEmpty() && !segundoApellido.matches(regex))) {
            throw new IllegalArgumentException("El nombre solo puede contener letras, espacios, guiones y tildes.");
        }
    }

    public String getPrimerNombre() { return primerNombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public String getPrimerApellido() { return primerApellido; }
    public String getSegundoApellido() { return segundoApellido; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NombreCompleto)) return false;
        NombreCompleto that = (NombreCompleto) o;
        return primerNombre.equals(that.primerNombre) &&
                segundoNombre.equals(that.segundoNombre) &&
                primerApellido.equals(that.primerApellido) &&
                segundoApellido.equals(that.segundoApellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primerNombre, segundoNombre, primerApellido, segundoApellido);
    }

    @Override
    public String toString() {
        return String.join(" ", primerNombre, segundoNombre.isEmpty() ? "" : segundoNombre, primerApellido,
                segundoApellido.isEmpty() ? "" : segundoApellido).replaceAll("\\s{2,}", " ").trim();
    }
}