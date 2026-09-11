package org.llin.demo.northwind.config;

import org.llin.demo.northwind.service.CustomOAuth2UserService;
import org.llin.demo.northwind.service.CustomUserDetailsService; // Explicitly import your custom implementation
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.remember-me.secret}")
    private String rememberMeSecret;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Explicitly wire your CustomUserDetailsService by its precise class type 
     * to prevent Spring from defaulting to the internal in-memory manager.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(CustomUserDetailsService customUserDetailsService,
                                                           PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authenticationProvider,
                                                   CustomOAuth2UserService oAuth2UserService,
                                                   CustomUserDetailsService customUserDetailsService) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authenticationProvider(authenticationProvider) // Register the database custom provider context
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/forgotUser", "/login", "/register",
                                 "/requestNewPassword", "/requestPassword",
                                 "/setNewPassword", "/setPassword", "/verify",
                                 "/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/user/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .permitAll()
            )
            .oauth2Login(oauth -> oauth
                .loginPage("/login")
                .defaultSuccessUrl("/home", true)
                .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
            )
            .rememberMe(rememberMe -> rememberMe
                .userDetailsService(customUserDetailsService) // Correctly bind your custom service here as well
                .tokenValiditySeconds(1209600)
                .key(rememberMeSecret)
            )
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
            );

        return http.build();
    }
}
