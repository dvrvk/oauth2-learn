package com.pruebaoauth.service;


import com.google.api.client.auth.openidconnect.IdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.json.JsonFactory; // Importa JacksonFactory
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.Clock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleAuthService {

    // Este es el CLIENT_ID que debes obtener de la consola de desarrolladores de Google.
    @Value("${google.client-id}")
    private String CLIENT_ID;
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();


    public String verifyIdToken(String idTokenString) throws GeneralSecurityException, IOException {
        // Verificador de ID Token
        IdTokenVerifier verifier = new IdTokenVerifier.Builder()
                .setAudience(Collections.singletonList(CLIENT_ID)) // Verifica que el token sea para tu cliente
                .setIssuer("https://accounts.google.com")
                .setAcceptableTimeSkewSeconds(300L)  // Aceptar hasta 5 minutos de desfase de tiempo
                .setClock(Clock.SYSTEM) // Usar el reloj del sistema
                .build();


        // Verificación del ID Token
        GoogleIdToken idToken = GoogleIdToken.parse(JSON_FACTORY, idTokenString);

        if (idToken == null) {
            throw new IOException("Invalid ID Token");
        }

        if (verifier.verify(idToken)) {
            // Retorna el Google ID del usuario
            return idToken.getPayload().getSubject();  // Esto nos da el Google ID
        } else {
            throw new IOException("ID Token is invalid or has been tampered with.");
        }
    }
}

