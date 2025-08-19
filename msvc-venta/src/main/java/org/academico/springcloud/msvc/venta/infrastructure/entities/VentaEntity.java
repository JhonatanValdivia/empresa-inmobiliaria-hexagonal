package org.academico.springcloud.msvc.venta.infrastructure.entities;

import org.academico.springcloud.msvc.venta.domain.models.enums.EstadoVenta;
import org.academico.springcloud.msvc.venta.domain.models.enums.TipoVenta;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.FechaVentaInfr;
import org.academico.springcloud.msvc.venta.infrastructure.entities.infravalueobjects.PrecioVentaInfr;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ventas")
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoVenta tipoVenta;

    @Enumerated(EnumType.STRING)
    private EstadoVenta estado;

    @Embedded
    private FechaVentaInfr fecha;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "precioVenta", column = @Column(name = "precio_valor")),
            @AttributeOverride(name = "moneda", column = @Column(name = "precio_moneda"))
    })
    private PrecioVentaInfr precioVenta;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DetalleVentaEntity> detalleVentaLista = new ArrayList<>();

    // NUEVO: Campo para el ID de la Preventa. No es una relación JPA directa con PreventaEntity.
    @Column(name = "id_preventa", unique = true, nullable = true) // id_preventa puede ser nulo si no hay preventa
    private Long idPreventa;

    public VentaEntity() {}

    // MODIFICADO: Constructor completo, añade idPreventa
    public VentaEntity(Long id, TipoVenta tipoVenta, EstadoVenta estado, FechaVentaInfr fecha, PrecioVentaInfr precioVenta, List<DetalleVentaEntity> detalleVentaLista, Long idPreventa) {
        this.id = id;
        this.tipoVenta = tipoVenta;
        this.estado = estado;
        this.fecha = fecha;
        this.precioVenta = precioVenta;
        this.detalleVentaLista = (detalleVentaLista != null) ? new ArrayList<>(detalleVentaLista) : new ArrayList<>();
        // NUEVO: Asignación de idPreventa
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

    public FechaVentaInfr getFecha() {
        return fecha;
    }

    public void setFecha(FechaVentaInfr fecha) {
        this.fecha = fecha;
    }

    public PrecioVentaInfr getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(PrecioVentaInfr precioVenta) {
        this.precioVenta = precioVenta;
    }

    public List<DetalleVentaEntity> getDetalleVentaLista() {
        return detalleVentaLista;
    }

    public void setDetalleVentaLista(List<DetalleVentaEntity> detalleVentaLista) {
        this.detalleVentaLista = detalleVentaLista;
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
        VentaEntity that = (VentaEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}