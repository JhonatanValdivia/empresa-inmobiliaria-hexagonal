package org.academico.springcloud.msvc.venta.domain.models.domainentities;

import org.academico.springcloud.msvc.venta.domain.models.enums.EstadoVenta;
import org.academico.springcloud.msvc.venta.domain.models.valueobjects.FechaVenta;
import org.academico.springcloud.msvc.venta.domain.models.valueobjects.PrecioVenta;
import org.academico.springcloud.msvc.venta.domain.models.enums.TipoVenta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Venta {
    private Long id;
    private TipoVenta tipoVenta;
    private EstadoVenta estado;
    private FechaVenta fecha;
    private PrecioVenta precioVenta;
    private List<DetalleVenta> detalleVentaLista = new ArrayList<>();
    // NUEVO: Campo para el ID de la Preventa relacionada
    private Long idPreventa;

    public Venta() {
        this.estado = EstadoVenta.RESERVADA; // Valor por defecto
    }

    // MODIFICADO: Constructor completo, añade idPreventa
    public Venta(Long id, TipoVenta tipoVenta, EstadoVenta estado, FechaVenta fecha, PrecioVenta precioVenta, List<DetalleVenta> detalleVentaLista, Long idPreventa) {
        if (tipoVenta == null) {
            throw new IllegalArgumentException("El tipo de venta es obligatorio.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha de venta es obligatoria.");
        }
        if (precioVenta == null) {
            throw new IllegalArgumentException("El precio de venta es obligatorio.");
        }

        this.id = id;
        this.tipoVenta = tipoVenta;
        this.estado = (estado != null) ? estado : EstadoVenta.RESERVADA;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
        this.detalleVentaLista = (detalleVentaLista != null) ? new ArrayList<>(detalleVentaLista) : new ArrayList<>();
        // NUEVO: Asignación de idPreventa
        this.idPreventa = idPreventa;
    }

    // Métodos de agregado (lógica de negocio que opera sobre el conjunto Venta + DetalleVenta)
    public void agregarDetalleVenta(DetalleVenta detalleVenta) {
        if (detalleVenta == null) {
            throw new IllegalArgumentException("El detalle de venta no puede ser nulo.");
        }
        // Podrías añadir validaciones aquí, ej. que el detalle no esté ya en la lista
        if (detalleVenta.getId() != null &&
                this.detalleVentaLista.stream().anyMatch(d -> d.getId().equals(detalleVenta.getId()))) {
            throw new IllegalArgumentException("El detalle de venta con ID " + detalleVenta.getId() + " ya existe en esta venta.");
        }
        this.detalleVentaLista.add(detalleVenta);
    }

    public void eliminarDetalleVenta(Long detalleId) {
        DetalleVenta detalleAEliminar = this.detalleVentaLista.stream()
                .filter(d -> Objects.equals(d.getId(), detalleId))
                .findFirst()
                .orElse(null);

        if (detalleAEliminar == null) {
            throw new IllegalArgumentException("Detalle de venta con ID " + detalleId + " no encontrado en esta venta.");
        }
        this.detalleVentaLista.remove(detalleAEliminar);
    }

    // NUEVO: Método de lógica de negocio para asignar preventa
    public void asignarPreventa(Long idPreventa, String estadoPreventa) {
        if (idPreventa == null) {
            throw new IllegalArgumentException("El ID de la preventa no puede ser nulo para asignar.");
        }
        if (!"APROBADA".equals(estadoPreventa)) { // Validación clave
            throw new IllegalStateException("La preventa debe estar APROBADA para ser asignada a una venta. Estado actual: " + estadoPreventa);
        }
        if (this.idPreventa != null && !this.idPreventa.equals(idPreventa)) {
            throw new IllegalStateException("Esta venta ya tiene una preventa asignada: " + this.idPreventa);
        }
        this.idPreventa = idPreventa;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public EstadoVenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoVenta estado) {
        this.estado = estado;
    }

    public FechaVenta getFecha() {
        return fecha;
    }

    public void setFecha(FechaVenta fecha) {
        this.fecha = fecha;
    }

    public PrecioVenta getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(PrecioVenta precioVenta) {
        this.precioVenta = precioVenta;
    }

    public List<DetalleVenta> getDetalleVentaLista() {
        return new ArrayList<>(detalleVentaLista); // Devuelve una copia para evitar modificación externa
    }

    public void setDetalleVentaLista(List<DetalleVenta> detalleVentaLista) {
        this.detalleVentaLista = (detalleVentaLista != null) ? new ArrayList<>(detalleVentaLista) : new ArrayList<>();
    }

    // NUEVO: Getter y Setter para idPreventa
    public Long getIdPreventa() {
        return idPreventa;
    }

    public void setIdPreventa(Long idPreventa) {
        this.idPreventa = idPreventa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Venta venta = (Venta) o;
        return Objects.equals(id, venta.id); // La igualdad por ID es común para entidades de dominio
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}