package com.zambetti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.function.client.WebClient;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {
    /*TODO: add role inside of JWT
        @Autowired
        private JwtFilter jwtFilter;
    */
    @Bean
    public SecurityWebFilterChain configure(ServerHttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeExchange()
                .anyExchange().permitAll();
        return http.build();
    }

}