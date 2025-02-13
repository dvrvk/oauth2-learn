package com.pruebaoauth.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    String hello(@AuthenticationPrincipal OidcUser oidcUser) {
        return "Hello%s, email: %s, token: %s".formatted(oidcUser.getFullName(), oidcUser.getEmail(), oidcUser.getIdToken());
    }
}
