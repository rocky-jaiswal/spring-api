package dev.rockyj.springapi.configuration;

import org.springframework.boot.autoconfigure.security.reactive.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration(proxyBeanMethods = false)
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange((exchange) -> {
            exchange.matchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
            exchange.pathMatchers("/v1/**").permitAll();
            exchange.anyExchange().authenticated();
        });

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);

        http.formLogin(ServerHttpSecurity.FormLoginSpec::disable);
        return http.build();
    }

}
