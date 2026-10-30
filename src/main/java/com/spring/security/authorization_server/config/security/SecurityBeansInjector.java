package com.spring.security.authorization_server.config.security;


import com.spring.security.authorization_server.exeption.ObjectNotFoundException;
import com.spring.security.authorization_server.persistance.repository.security.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// Se una esta anotación para poder inyectar beans
@Configuration
// Esta clase es un inyector de beans de seguridad
// Contiene definiciones de beans que springboot debe registrar en su contexto
public class SecurityBeansInjector {

    @Autowired
    private IUserRepository userRepository;
    /* esta clase AuthenticationConfiguration ya es proveída por spring security también podria inyectarlo directamente en los
     * parametros del metodo authenticationManager nos proporciona una implementación más específica del AuthenticationManager(administrador de autenticaciones)
    */

    private AuthenticationConfiguration authenticationConfiguration;

    // Este bean hace que spring boot registre y administre el objeto AuthenticationManager que devuelve el método authenticationManager
    @Bean
    public AuthenticationManager authenticationManager()throws  Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    // Estrategia de authenticación
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationStrategy = new DaoAuthenticationProvider(userDetailsService());
        // DaoAuthenticationProvider necesita un password encoder porque las contraseñas estarán encriptadas
        authenticationStrategy.setPasswordEncoder(passwordEncoder()); // necesita un codificador de contraseñas para comparar cuando se inicia sicion
        return authenticationStrategy;
    }

    @Bean
    PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService (){
        return (String username) -> {
            return userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Username not fount with user " + username));
        };
    }


}
