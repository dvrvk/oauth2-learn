package com.pruebaoauth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único, utilizado en ambos casos (tradicional y con Google)

    private String googleId; // ID único de Google (puede ser nulo si el usuario no usa Google)
    private String email;
    private String password; // Contraseña (será usada solo si el usuario no inicia sesión con Google)
    private String nombre;
    private LocalDateTime creadoEn;

    public Usuario() {}

    // Constructor para usuarios con ID normal (sin Google)
    public Usuario(String email, String nombre) {
        this.email = email;
        this.nombre = nombre;
        this.creadoEn = LocalDateTime.now();
    }

    // Constructor para usuarios con Google ID
    public Usuario(String googleId, String email, String nombre) {
        this.googleId = googleId;
        this.email = email;
        this.nombre = nombre;
        this.creadoEn = LocalDateTime.now();
    }


}

