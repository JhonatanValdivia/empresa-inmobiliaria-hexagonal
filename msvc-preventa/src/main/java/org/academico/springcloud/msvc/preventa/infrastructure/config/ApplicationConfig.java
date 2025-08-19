package org.academico.springcloud.msvc.preventa.infrastructure.config;

import org.academico.springcloud.msvc.preventa.application.services.ContratoService;
import org.academico.springcloud.msvc.preventa.application.services.PreventaService;
import org.academico.springcloud.msvc.preventa.application.services.PropuestaService;
import org.academico.springcloud.msvc.preventa.application.services.VisitaService;
import org.academico.springcloud.msvc.preventa.application.usescases.contrato.*;
import org.academico.springcloud.msvc.preventa.application.usescases.preventa.*;
import org.academico.springcloud.msvc.preventa.application.usescases.propuesta.*;
import org.academico.springcloud.msvc.preventa.application.usescases.visita.*;
import org.academico.springcloud.msvc.preventa.domain.ports.in.contrato.*;
import org.academico.springcloud.msvc.preventa.domain.ports.in.preventa.*;
import org.academico.springcloud.msvc.preventa.domain.ports.in.propuesta.*;
import org.academico.springcloud.msvc.preventa.domain.ports.in.visita.*;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PreventaRepositorioPort;
import org.academico.springcloud.msvc.preventa.domain.ports.out.PropiedadPort;
import org.academico.springcloud.msvc.preventa.domain.ports.out.UsuarioPort;
import org.academico.springcloud.msvc.preventa.infrastructure.adapters.ExternalPropiedadesAdapter;
import org.academico.springcloud.msvc.preventa.infrastructure.adapters.ExternalUsuarioAdapter;
import org.academico.springcloud.msvc.preventa.infrastructure.adapters.PreventaRepositorioAdapter;
import org.academico.springcloud.msvc.preventa.infrastructure.clients.PropiedadClientRest;
import org.academico.springcloud.msvc.preventa.infrastructure.repositories.JpaPreventaRepositorio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PreventaService preventaServicio(PreventaRepositorioPort preventaRepositorioPort,
                                            UsuarioPort usuarioPort,
                                            PropiedadPort propiedadPort) {
      return new PreventaService(
              new ActualizarPreventaCasoUsoImpl(preventaRepositorioPort),
              new AprobarPreventaCasoUsoImpl(preventaRepositorioPort),
              new CrearPreventaCasoUsoImpl(preventaRepositorioPort),
              new EliminarPreventaCasoUsoImpl(preventaRepositorioPort),
              new RecuperarPreventaCasoUsoImpl(preventaRepositorioPort, propiedadPort, usuarioPort),
              new AsociarUsuarioPreventaImpl(preventaRepositorioPort, usuarioPort)
        );
    }

    @Bean
    public UsuarioPort usuarioPort(ExternalUsuarioAdapter externalUsuarioAdapter) {
        return externalUsuarioAdapter;
    }

    @Bean
    public ContratoService contratoService(
            ActualizarContratoVentaCasoUso actualizarContratoVentaCasoUso,
            AgregarContratoCasoUso agregarContratoCasoUso,
            AnularContratoPreventaCasoUso anularContratoPreventaCasoUso,
            EliminarContratoVentaCasoUso eliminarContratoVentaCasoUso,
            ObtenerContratoPorPreventaCasoUso obtenerContratoPorPreventaCasoUso) {
        return new ContratoService(
                actualizarContratoVentaCasoUso,
                agregarContratoCasoUso,
                anularContratoPreventaCasoUso,
                eliminarContratoVentaCasoUso,
                obtenerContratoPorPreventaCasoUso
        );
    }

    @Bean
    public PropuestaService propuestaService(
            AceptarPropuestaPagoCasoUso aceptarPropuestaPagoCasoUso,
            ActualizarPropuestaPagoCasoUso actualizarPropuestaPagoCasoUso,
            AgregarPropuestaPagoCasoUso agregarPropuestaPagoCasoUso,
            ListarPropuestasPagoCasoUso listarPropuestasPagoCasoUso,
            ObtenerPropuestaPagoPorIdCasoUso obtenerPropuestaPagoPorIdCasoUso) {
        return new PropuestaService(
                aceptarPropuestaPagoCasoUso,
                actualizarPropuestaPagoCasoUso,
                agregarPropuestaPagoCasoUso,
                listarPropuestasPagoCasoUso,
                obtenerPropuestaPagoPorIdCasoUso
        );
    }

    @Bean
    public VisitaService visitaService(
            ActualizarEstadoVisitaCasoUso actualizarEstadoVisitaCasoUso,
            ActualizarVisitaProgramadaCasoUso actualizarVisitaProgramadaCasoUso,
            AgregarVisitaProgramadaCasoUso agregarVisitaProgramadaCasoUso,
            ListarVisitasProgramadasCasoUso listarVisitasProgramadasCasoUso,
            ReprogramarVisitaCasoUso reprogramarVisitaCasoUso) {
        return new VisitaService(
                actualizarEstadoVisitaCasoUso,
                actualizarVisitaProgramadaCasoUso,
                agregarVisitaProgramadaCasoUso,
                listarVisitasProgramadasCasoUso,
                reprogramarVisitaCasoUso
        );
    }

    // Implementaciones de Casos de Uso (Preventa)
    @Bean
    public ActualizarPreventaCasoUso actualizarPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ActualizarPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public AprobarPreventaCasoUso aprobarPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AprobarPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public CrearPreventaCasoUso crearPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new CrearPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public EliminarPreventaCasoUso eliminarPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new EliminarPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public RecuperarPreventaCasoUso recuperarPreventaCasoUso(
            PreventaRepositorioPort preventaRepositorioPort,
            PropiedadPort propiedadPort,
            UsuarioPort usuarioPort) {
        return new RecuperarPreventaCasoUsoImpl(preventaRepositorioPort, propiedadPort, usuarioPort);
    }

    // Implementaciones de Casos de Uso (Contrato)
    @Bean
    public ActualizarContratoVentaCasoUso actualizarContratoVentaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ActualizarContratoVentaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public AgregarContratoCasoUso agregarContratoCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AgregarContratoCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public AnularContratoPreventaCasoUso anularContratoPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AnularContratoPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public EliminarContratoVentaCasoUso eliminarContratoVentaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new EliminarContratoVentaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ObtenerContratoPorPreventaCasoUso obtenerContratoPorPreventaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ObtenerContratoPorPreventaCasoUsoImpl(preventaRepositorioPort);
    }

    // Implementaciones de Casos de Uso (Propuesta)
    @Bean
    public AceptarPropuestaPagoCasoUso aceptarPropuestaPagoCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AceptarPropuestaPagoCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ActualizarPropuestaPagoCasoUso actualizarPropuestaPagoCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ActualizarPropuestaPagoCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public AgregarPropuestaPagoCasoUso agregarPropuestaPagoCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AgregarPropuestaPagoCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ListarPropuestasPagoCasoUso listarPropuestasPagoCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ListarPropuestasPagoCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ObtenerPropuestaPagoPorIdCasoUso obtenerPropuestaPagoPorIdCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ObtenerPropuestaPagoPorIdCasoUsoImpl(preventaRepositorioPort);
    }

    // Implementaciones de Casos de Uso (Visita)
    @Bean
    public ActualizarEstadoVisitaCasoUso actualizarEstadoVisitaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ActualizarEstadoVisitaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ActualizarVisitaProgramadaCasoUso actualizarVisitaProgramadaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ActualizarVisitaProgramadaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public AgregarVisitaProgramadaCasoUso agregarVisitaProgramadaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new AgregarVisitaProgramadaCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ListarVisitasProgramadasCasoUso listarVisitasProgramadasCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ListarVisitasProgramadasCasoUsoImpl(preventaRepositorioPort);
    }

    @Bean
    public ReprogramarVisitaCasoUso reprogramarVisitaCasoUso(PreventaRepositorioPort preventaRepositorioPort) {
        return new ReprogramarVisitaCasoUsoImpl(preventaRepositorioPort);
    }

    // Adaptador para Repositorio
    // Ya no se inyecta PreventaMapper aquí, ya que sus métodos son estáticos.
    @Bean
    public PreventaRepositorioPort preventaRepositorioPort(JpaPreventaRepositorio jpaPreventaRepositorio) {
        return new PreventaRepositorioAdapter(jpaPreventaRepositorio);
    }

    // --- ADAPTADOR DE PROPIEDADES ---
    @Bean
    public PropiedadPort propiedadPort(PropiedadClientRest propiedadClientRest) {
        return new ExternalPropiedadesAdapter(propiedadClientRest);
    }
}