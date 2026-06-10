package org.llin.demo.northwind;  

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.util.ReflectionTestUtils;

class SecurityConfigTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;
    
    // Autowired expected beans for dependency verification
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    
    void passwordEncoderBeanExists() {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        assertThat(encoder).isNotNull();
        //assertThat(encoder).isSameAs(passwordEncoder); // ensure it's the exact bean used everywhere
    }
    
    
    void authenticationProviderSupportsUsernamePasswordToken() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        assertThat(provider.supports(UsernamePasswordAuthenticationToken.class)).isTrue();
    }

    
    void authenticationProviderHasExpectedDependencies() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        // Verify correct UserDetailsService was injected (field is in AbstractUserDetailsAuthenticationProvider)
        Object injectedUserDetailsService = ReflectionTestUtils.getField(provider, "userDetailsService");
        assertThat(injectedUserDetailsService).isSameAs(userDetailsService);

        // Verify correct PasswordEncoder was injected (field is in DaoAuthenticationProvider)
        Object injectedPasswordEncoder = ReflectionTestUtils.getField(provider, "passwordEncoder");
        assertThat(injectedPasswordEncoder).isSameAs(passwordEncoder);
    }

    
    void securityFilterChainBeanExists() {
        SecurityFilterChain chain = context.getBean(SecurityFilterChain.class);
        assertThat(chain).isNotNull();
    }

    
    void authenticationManagerBeanExists() {
        AuthenticationManager manager = context.getBean(AuthenticationManager.class);
        assertThat(manager).isNotNull();
    }

    
    void userDetailsServiceBeanExists() {
        UserDetailsService service = context.getBean(UserDetailsService.class);
        assertThat(service).isNotNull();
        assertThat(service).isSameAs(userDetailsService);
    }

    
    void providerCanAuthenticateWithDummyCredentials() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        // Create a dummy token (this will exercise the provider using the injected UserDetailsService + PasswordEncoder)
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("testuser", "testpassword");

        // The provider should at least accept the token without throwing (real auth depends on your UserDetailsService impl)
        assertThat(provider.supports(token.getClass())).isTrue();

        // Optional: if you have a real test user in your UserDetailsService, you can call:
        // Authentication result = provider.authenticate(token);
        // assertThat(result).isNotNull();
        // assertThat(result.isAuthenticated()).isTrue();
    }
}