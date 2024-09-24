package com.mytrendfavapi.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().authenticated()  // Exige autenticação para todos os endpoints
                )
                .formLogin(form ->
                        form
                                .loginPage("/login")  // Define a página de login
                                .permitAll()          // Permite acesso à página de login sem autenticação
                )
                .logout(logout ->
                        logout
                                .permitAll()          // Permite fazer logout
                )
                .csrf(csrf -> csrf.disable());  // Usando o método correto para desabilitar CSRF

        return http.build();
    }

    // Configura o usuário em memória com senha criptografada usando BCrypt
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("Matheus")
                .password(passwordEncoder().encode("1234"))  // Usa BCrypt para criptografar a senha
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Usa BCrypt para criptografia de senha
    }
}
