package com.pruebaoauth.service;

import com.pruebaoauth.model.Usuario;
import com.pruebaoauth.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Crear un nuevo usuario con Google.
     */
    // Crear un usuario con Google (solo con googleId)
    public Usuario crearUsuarioConGoogle(String googleId) {
        Usuario usuario = new Usuario();
        usuario.setGoogleId(googleId);
        usuario.setNombre("Usuario de Google"); // Puedes usar otro nombre predeterminado o obtenerlo desde el token
        usuario.setEmail(googleId + "@gmail.com"); // Generar un email ficticio, por ejemplo

        // Guardar el usuario en la base de datos
        return usuarioRepository.save(usuario);
    }

    /**
     * Crear un nuevo usuario normal (sin Google).
     */
    public Usuario crearUsuarioNormal(String email, String nombre) {
        if (usuarioRepository.existsByEmail(email)) {
            return null; // Si el email ya existe, no crear el usuario
        }
        Usuario usuario = new Usuario(email, nombre); // Crear usuario normal
        return usuarioRepository.save(usuario); // Guardar en la base de datos
    }

    /**
     * Obtener un usuario por su googleId.
     */
    public Usuario obtenerUsuarioPorGoogleId(String googleId) {
        return usuarioRepository.findByGoogleId(googleId); // Buscar por googleId
    }

    /**
     * Obtener un usuario por su email.
     */
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email); // Buscar por email
    }

    /**
     * Verificar si existe un usuario por su googleId.
     */
    public boolean existePorGoogleId(String googleId) {
        return usuarioRepository.existsByGoogleId(googleId); // Verificar si existe por googleId
    }

    /**
     * Verificar si existe un usuario por su email.
     */
    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email); // Verificar si existe por email
    }
}


