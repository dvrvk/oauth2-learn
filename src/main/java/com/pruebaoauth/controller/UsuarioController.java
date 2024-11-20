package com.pruebaoauth.controller;

import com.pruebaoauth.model.Usuario;
import com.pruebaoauth.service.GoogleAuthService;
import com.pruebaoauth.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private GoogleAuthService googleAuthService;

    // Crear un nuevo usuario normal (sin Google)
    @PostMapping("/normal")
    public ResponseEntity<Usuario> crearUsuarioNormal(@RequestBody Usuario usuario) {
        if (usuarioService.existePorEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().build(); // Si el email ya existe, no crear usuario
        }
        Usuario nuevoUsuario = usuarioService.crearUsuarioNormal(usuario.getEmail(), usuario.getNombre());
        return ResponseEntity.ok(nuevoUsuario);
    }

    // Endpoint para crear un nuevo usuario con Google
    @PostMapping("/google")
    public ResponseEntity<Usuario> crearUsuarioConGoogle(@RequestParam String idToken) {
        try {
            // Verificamos el idToken con Google
            String googleId = googleAuthService.verifyIdToken(idToken);

            // Verificamos si el usuario ya existe en la base de datos usando el googleId
            if (usuarioService.existePorGoogleId(googleId)) {
                return ResponseEntity.badRequest().build(); // Si ya existe, no lo creamos
            }

            // Si no existe, creamos un nuevo usuario con el googleId
            Usuario nuevoUsuario = usuarioService.crearUsuarioConGoogle(googleId);

            // Respondemos con el nuevo usuario
            return ResponseEntity.ok(nuevoUsuario);

        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Error en la verificación del token o creación
        }
    }

    // Obtener un usuario por su googleId
    @GetMapping("/google/{googleId}")
    public ResponseEntity<Usuario> obtenerUsuarioPorGoogleId(@PathVariable String googleId) {
        Usuario usuario = usuarioService.obtenerUsuarioPorGoogleId(googleId);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build(); // Usuario no encontrado
        }
    }

    // Obtener un usuario por su email
    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.notFound().build(); // Usuario no encontrado
        }
    }
}

