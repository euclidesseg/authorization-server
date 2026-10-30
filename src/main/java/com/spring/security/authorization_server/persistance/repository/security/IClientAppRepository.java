package com.spring.security.authorization_server.persistance.repository.security;

import com.spring.security.authorization_server.persistance.entity.security.ClientApp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientAppRepository extends JpaRepository<ClientApp, Long> {

    Optional<ClientApp> findByClientId(String clientId);
}
