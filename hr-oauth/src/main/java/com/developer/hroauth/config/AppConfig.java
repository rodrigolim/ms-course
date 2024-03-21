package com.developer.hroauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AppConfig {

    //https://www.youtube.com/watch?v=alv0YgPDdOQ
    @Bean
    public BCryptPasswordEncoder passwordEncode(){
        return new BCryptPasswordEncoder();
    }

}
