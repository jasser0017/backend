package com.example.projet_fin_annee.Configuration;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class KeycloakConfig {

   /* @Value("${app.keycloak.admin.clientId}")
    private String adminClientId;


    @Value("${app.keycloak.serverUrl}")
    private String authServerUrl;

    @Value("${app.keycloak.realm}")
    private String realm;
    

    @Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()
                .serverUrl(authServerUrl)
                .realm(realm)
                .clientId(adminClientId)
               // .clientSecret(adminClientSecret)
                .username("jasserallela")
                .password("jasserallela123")
                .grantType("password")
                .build();
    }*/
}
