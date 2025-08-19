package org.academico.springcloud.msvc.usuario.infrastructure.controllers;

import jakarta.validation.Valid;
import org.academico.springcloud.msvc.usuario.domain.models.entities.Usuario;
import org.academico.springcloud.msvc.usuario.domain.models.enums.TipoUsuario;
import org.academico.springcloud.msvc.usuario.domain.ports.in.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioController {

    @Autowired
    private CrearUsuarioUseCase crearUsuarioUseCase;
    @Autowired
    private ObtenerUsuarioUseCase obtenerUsuarioUseCase;
    @Autowired
    private ActualizarUsuarioUseCase actualizarUsuarioUseCase;
    @Autowired
    private EliminarUsuarioUseCase eliminarUsuarioUseCase;
    @Autowired
    private ContarUsuariosUseCase contarUsuariosUseCase;
    @Autowired
    private ObtenerUsuariosPorTipoUseCase obtenerUsuariosPorTipoUseCase;
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(obtenerUsuarioUseCase.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalleUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOp = obtenerUsuarioUseCase.obtenerPorId(id);
        return usuarioOp.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors());
        }
        try {
            Usuario usuarioDB = crearUsuarioUseCase.crearUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDB);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldErrors());
        }
        try {
            Usuario usuarioDB = actualizarUsuarioUseCase.actualizarUsuario(id, usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDB);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Usuario> usuarioOp = obtenerUsuarioUseCase.obtenerPorId(id);
        if (usuarioOp.isPresent()) {
            eliminarUsuarioUseCase.eliminarUsuario(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/contar")
    public ResponseEntity<?> contarUsuarios() {
        return ResponseEntity.ok(contarUsuariosUseCase.contarUsuarios());
    }

    @GetMapping("/tipo/{tipoUsuario}")
    public ResponseEntity<?> obtenerPorTipo(@PathVariable TipoUsuario tipoUsuario) {
        List<Usuario> usuarios = obtenerUsuariosPorTipoUseCase.obtenerPorTipo(tipoUsuario);
        return ResponseEntity.ok(usuarios);
    }

    @RestControllerAdvice
    public class GlobalExceptionHandler {
        @ExceptionHandler(IllegalArgumentException.class)
        public ResponseEntity<?> handleIllegalArgument(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<?> handleGenericException(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }


}