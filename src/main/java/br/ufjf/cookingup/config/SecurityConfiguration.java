package br.ufjf.cookingup.config;

import br.ufjf.cookingup.security.JwtAuthFilter;
import br.ufjf.cookingup.security.JwtService;
import br.ufjf.cookingup.model.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public SecurityConfiguration(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(usuarioService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // replacement for the old AuthenticationManagerBuilder injection
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v1/administradores/**").hasAnyRole("ADMIN")
                .requestMatchers("/api/v1/alternativas/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers("/api/v1/avaliacoes/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers("/api/v1/categorias/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers("/api/v1/chefs/**").hasAnyRole("CHEF", "ADMIN")
                .requestMatchers("/api/v1/ingredientes/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers("/api/v1/livros/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers("/api/v1/membros/**").hasAnyRole("MEMBRO", "ADMIN")
                .requestMatchers("/api/v1/receitas/**").hasAnyRole("MEMBRO", "CHEF", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/v1/usuarios/**").permitAll()
                .requestMatchers(
                	    "/v2/api-docs",
                	    "/configuration/ui",
                	    "/swagger-resources/**",
                	    "/configuration/security",
                	    "/swagger-ui.html",
                	    "/webjars/**"
                	).permitAll()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
