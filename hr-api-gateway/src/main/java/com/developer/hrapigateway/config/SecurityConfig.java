package com.developer.hrapigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.reactive.CorsConfigurationSource;


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/eureka/**",
            "/webjars/**", "/swagger-ui.html", "/v3/**",
            "/auth/public", "/auth/search",
            "/hr-payroll/actuator/**", "/hr-payroll/v3/**", "/hr-payroll/swagger-ui/**",
            "/hr-user/actuator/**", "/hr-user/v3/**", "/hr-user/swagger-ui/**",
            "/hr-worker/actuator/**", "/hr-worker/v3/**", "hr-worker/swagger-ui/**"};

    @Autowired
    private CorsConfigurationSource corsConfigurationSource;

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange(exchanges -> exchanges
                        .pathMatchers(AUTH_WHITELIST).permitAll()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(corsConfigurationSource));

        return http.build();
    }


}