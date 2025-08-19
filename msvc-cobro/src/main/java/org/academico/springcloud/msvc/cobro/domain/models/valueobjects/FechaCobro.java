package org.academico.springcloud.msvc.cobro.domain.models.valueobjects;

import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Objects;

public class FechaCobro {

    private final int dia;
    private final int mes;
    private final int anio;
    private final int hora;
    private final int minuto;

    // Constructor sin argumentos (puede ser requerido por Spring si se usa en alguna parte)
    // Aunque en una arquitectura hexagonal, los VOs del dominio no deben tener conocimiento de persistencia.
    // Lo marcamos como 'protected' para limitar su uso.
    protected FechaCobro() {
        this.dia = 0;
        this.mes = 0;
        this.anio = 0;
        this.hora = 0;
        this.minuto = 0;
    }

    public FechaCobro(int dia, int mes, int anio, int hora, int minuto) {
        LocalDateTime now = LocalDateTime.now();
        int currentYear = now.getYear();

        if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 2000 || anio > currentYear + 10 ||
                hora < 0 || hora > 23 || minuto < 0 || minuto > 59) {
            throw new IllegalArgumentException("Fecha o hora inválida. Asegúrate de que el año no esté muy en el futuro.");
        }

        YearMonth yearMonth = YearMonth.of(anio, mes);
        if (dia > yearMonth.lengthOfMonth()) {
            throw new IllegalArgumentException("Día inválido para el mes y año especificados.");
        }

        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        this.hora = hora;
        this.minuto = minuto;
    }

    public FechaCobro(LocalDateTime fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha no puede ser nula.");
        }
        this.dia = fecha.getDayOfMonth();
        this.mes = fecha.getMonthValue();
        this.anio = fecha.getYear();
        this.hora = fecha.getHour();
        this.minuto = fecha.getMinute();
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%d %02d:%02d", dia, mes, anio, hora, minuto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FechaCobro that = (FechaCobro) o;
        return dia == that.dia && mes == that.mes && anio == that.anio && hora == that.hora && minuto == that.minuto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dia, mes, anio, hora, minuto);
    }
}