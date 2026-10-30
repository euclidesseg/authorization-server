package com.spring.security.authorization_server.persistance.entity.security;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClientApp {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientId;
    private String clientSecret;

    @ElementCollection(fetch = FetchType.EAGER)
    // crea una tabla llamada clientAuthenticationMethods realizando uno relacion de unos a muchos
    // entre ClientAplication y clientAuthenticationMethods
    private List<String> clientAuthenticationMethods;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> authorizationGrantTypes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> redirectUris;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> scopes;

    private int durationInMinutes;

    boolean requireProofKey;

    public boolean getRequireProofKey() {
        return requireProofKey;
    }
    public void setRequireProofKey(boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
    }
    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }

    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

}


// Importancia de que client App este en base de datos
// Resulta que las aplicaciones como google facebook github entre otras
// exponen un authorization server para que aplicaciones de terceros hagan uso de ellos entonces
// los creadores de esas apps cientes debemos ir a los sitios oficiales de essos authorization servers
// para registrar nuestra aplicacion cliente en esas bases de datos para que las apps se logren conectar