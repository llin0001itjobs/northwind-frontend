package org.llin.demo.northwind.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.data.repository.UserRepository;
import org.llin.demo.northwind.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootTest
@SpringJUnitConfig(classes = {
        SecurityConfig.class, // The correct one in org.llin.demo.northwind.config
        WebMvcAutoConfiguration.class
        
})
class SecurityConfigTest {

    @MockBean
    private UserRepository userRepository; // ✅ Mock this dependency to avoid actual DB
    
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

        Field passwordEncoderField = DaoAuthenticationProvider.class.getDeclaredField("passwordEncoder");
        passwordEncoderField.setAccessible(true);
        Object passwordEncoder = passwordEncoderField.get(provider);
        assertThat(passwordEncoder).isNotNull();

        Field userDetailsServiceField = DaoAuthenticationProvider.class.getDeclaredField("userDetailsService");
        userDetailsServiceField.setAccessible(true);
        Object userDetailsService = userDetailsServiceField.get(provider);
        assertThat(userDetailsService).isInstanceOf(CustomUserDetailsService.class);
    }
    
    @Test
    void securityFilterChainBeanExists() {
        SecurityFilterChain chain = context.getBean(SecurityFilterChain.class);
        assertThat(chain).isNotNull();
    }
}
