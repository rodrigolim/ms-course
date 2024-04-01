package com.developer.hrapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] PUBLIC = {"/auth/public", "/auth/search",
                                            "/eureka/**",
                                            "/hr-worker/actuator/**", "hr-worker/swagger-ui/**"};

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers(PUBLIC).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(withDefaults()));

       // http.csrf(ServerHttpSecurity.CsrfSpec::disable);
        return http.build();
    }


}