package com.pruebaoauth.repository;

import com.pruebaoauth.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Buscar un usuario por email
    Usuario findByEmail(String email);

    // Buscar un usuario por googleId
    Usuario findByGoogleId(String googleId);

    // Verificar si existe un usuario por email
    boolean existsByEmail(String email);

    // Verificar si existe un usuario por googleId
    boolean existsByGoogleId(String googleId);
}


