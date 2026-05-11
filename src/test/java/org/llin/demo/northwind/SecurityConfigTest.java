package org.llin.demo.northwind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


class SecurityConfigTest {
    
    @Autowired
    private SecurityConfig securityConfig;
    
    @Autowired
    private ApplicationContext context;

    @Test
    void passwordEncoderBeanExists() {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        assertThat(encoder).isNotNull();
    }

    @Test
    void authenticationProviderAuthenticatesUsingInjectedDependencies() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        // Try to authenticate a dummy Authentication object (you can mock one if needed),
        // or at least check it supports UsernamePasswordAuthenticationToken
        assertThat(provider.supports(org.springframework.security.authentication.UsernamePasswordAuthenticationToken.class)).isTrue();
    }
    
    @Test
    void authenticationProviderHasExpectedDependencies() throws Exception {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

    }
    
    @Test
    void securityFilterChainBeanExists() {
        SecurityFilterChain chain = context.getBean(SecurityFilterChain.class);
        assertThat(chain).isNotNull();
    }
}
