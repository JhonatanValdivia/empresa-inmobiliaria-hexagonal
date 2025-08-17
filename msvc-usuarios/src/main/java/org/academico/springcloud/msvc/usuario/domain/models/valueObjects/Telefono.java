package org.academico.springcloud.msvc.usuario.domain.models.valueObjects;

import java.util.Objects;

public class Telefono {
    private String numero;
    private String codigoPais;

    protected Telefono() {}

    public Telefono(String numero, String codigoPais) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new IllegalArgumentException("El número es obligatorio.");
        }

        this.numero = numero.trim().replaceAll("[^\\d]", "");
        this.codigoPais = (codigoPais != null) ? codigoPais.trim() : null;

        if (!this.numero.matches("^\\d{6,15}$")) {
            throw new IllegalArgumentException("El número debe tener entre 6 y 15 dígitos.");
        }
        if (this.codigoPais != null && !this.codigoPais.matches("^\\+\\d{1,4}$")) {
            throw new IllegalArgumentException("El código de país debe comenzar con '+' seguido de 1 a 4 dígitos.");
        }
    }

    public String getNumero() { return numero; }
    public String getCodigoPais() { return codigoPais; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefono)) return false;
        Telefono that = (Telefono) o;
        return numero.equals(that.numero) && Objects.equals(codigoPais, that.codigoPais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, codigoPais);
    }

    @Override
    public String toString() {
        return (codigoPais != null ? codigoPais + " " : "") + numero;
    }
}