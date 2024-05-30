package com.challenge.forum.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.challenge.forum.api.utils.Constants.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
        throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                           .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                           .authorizeHttpRequests(a -> a.requestMatchers(HttpMethod.POST,
                                                                         AUTHENTICATION_ROUTE_WITH_WILDCARD
                           ).permitAll().requestMatchers(HttpMethod.GET,
                                                         TOPIC_CONTROLLER_ROUTE_MAP,
                                                         TOPIC_ROUTE_WITH_WILDCARD,
                                                         COURSE_CONTROLLER_ROUTE_MAP
                           ).permitAll().anyRequest().authenticated())
                           .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                           .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
        throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
