package com.developer.hroauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityFilterConfig {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class).oidc(withDefaults()); // Enable OpenID Connect 1.0
        return http.formLogin(Customizer.withDefaults()).build();
    }

    @Bean
    public SecurityFilterChain authorizationFilterChain(HttpSecurity http) throws Exception {

/*
        http.authorizeHttpRequests(authorize -> authorize
                .anyRequest().authenticated());
        return http.formLogin(Customizer.withDefaults()).build();
*/

        http.authorizeHttpRequests(authorizeConfig -> {
                    authorizeConfig.requestMatchers("/auth/public").permitAll();
                    authorizeConfig.requestMatchers("/auth/search").permitAll();
                    authorizeConfig.anyRequest().authenticated();
                })
                .formLogin(withDefaults())
                .oauth2ResourceServer(config -> {
                    config.jwt(Customizer.withDefaults());
                });

        return http.build();
    }


}
