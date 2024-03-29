package com.orgofarmsgroup.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@Slf4j
public class AppSecurityConfiguration {
    @Value("${allowed-origins:*}")
    private List<String> allowedOrigins;

    private final PasswordEncoder passwordEncoder;

    private static final String[] HTTP_GET_WHITE_LIST = {
            "/css/**", "/js/**", "/images/**",
            "/", "/index", "/*.html",
            "/error","/graphiql", "/graphql",
            "/logout",
            "/messages", "/messages/**"
    };

    private static final String[] HTTP_POST_WHITE_LIST = {
            "/messages", "/messages/**"
    };

    @Bean
    public InMemoryUserDetailsManager users() {
        log.info("setting in-memory-user-details-manager");
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .passwordEncoder(passwordEncoder::encode)
                        .password("user")
                        .roles("USER")
                        .build(),
                User.withUsername("admin")
                        .passwordEncoder(passwordEncoder::encode)
                        .password("admin")
                        .roles("USER","ADMIN")
                        .build()
        );
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth
                            .requestMatchers(HttpMethod.GET, HTTP_GET_WHITE_LIST).permitAll()
                            .requestMatchers("/actuator","/actuator/**").permitAll()
//                            .requestMatchers(HttpMethod.GET, HTTP_POST_WHITE_LIST).permitAll()
                            .requestMatchers("/admins","/admins/**").hasRole("ADMIN")
                            .anyRequest()
                            .authenticated();
                })
                .oauth2Login(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .logout(LogoutConfigurer::deleteCookies)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        log.info("setting cors configuration source");
        CorsConfiguration configuration = new CorsConfiguration();
        log.info("allowed origins {{}}", allowedOrigins);
        configuration.setAllowedOrigins(allowedOrigins);
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
