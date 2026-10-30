package com.spring.security.authorization_server.mapper;


import com.spring.security.authorization_server.persistance.entity.security.ClientApp;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;

import java.time.Duration;
import java.util.Date;


// La clase ClientAppMapper se encarga de convertir una entidad ClientApp en un objeto RegisteredClient,
// que es el modelo utilizado por Spring Authorization Server para representar un cliente registrado en el
// servidor de autorización.

public class ClientAppMapper {

    public static RegisteredClient toRegisteredClient(ClientApp clientApp) {

        RegisteredClient registeredClient = RegisteredClient.withId(clientApp.getClientId())
                .clientId(clientApp.getClientId())
                .clientSecret(clientApp.getClientSecret())
                .clientIdIssuedAt(new Date(System.currentTimeMillis()).toInstant())
                .clientAuthenticationMethods(clientAuthMethods -> {
                    clientApp.getClientAuthenticationMethods().stream()
                            .map(method -> new ClientAuthenticationMethod(method))
                            //.forEach(each -> clientAuthMethods.add(each));
                            .forEach(clientAuthMethods::add);
                })
                .authorizationGrantTypes(authGrantTypes ->{
                    clientApp.getAuthorizationGrantTypes().stream()
                            .map(grantType -> new AuthorizationGrantType(grantType))
                            .forEach(authGrantTypes::add);
                })
                .redirectUris(redirectUris ->
                        clientApp.getRedirectUris().stream().forEach(redirectUris::add))

                .scopes(scopes -> {
                    clientApp.getScopes().stream().forEach(scopes::add);
                })
                .tokenSettings(TokenSettings.builder()
                        .accessTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes()))
                        .refreshTokenTimeToLive(Duration.ofMinutes(clientApp.getDurationInMinutes()*4))
                        .build())

                .clientSettings(ClientSettings.builder()
                        .requireProofKey(clientApp.getRequireProofKey())
                        .build())
                .build();

        return registeredClient;
    }
}
//clientAuthenticationMethods: Define los métodos de autenticación que
// puede utilizar el cliente para autenticarse ante el servidor de autorización.


//authorizationGrantTypes: Define los tipos de concesión (Grant Types)
// que puede utilizar el cliente para obtener tokens de acceso.
// Por ejemplo, authorization_code, client_credentials y refresh_token.

// redirectUris: Define las URI a las que el servidor de autorización puede redirigir al usuario
// después de completar el proceso de autorización. Estas URI deben estar previamente registradas por seguridad.

//scopes: Define los permisos o niveles de acceso que el cliente puede solicitar al servidor de autorización

//tokenSettings: Permite configurar las características y duración de los tokens emitidos para el cliente. En este caso:

//clientSettings: Permite configurar características adicionales del cliente relacionadas con el proceso de autorización.
