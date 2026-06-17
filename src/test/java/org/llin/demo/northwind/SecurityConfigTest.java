package org.llin.demo.northwind;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;


@SpringBootTest
@ActiveProfiles("test")
class SecurityConfigTest extends SecurityBaseTest {

    @Autowired
    private ApplicationContext context;

    @MockBean
    private ClientRegistrationRepository clientRegistrationRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void passwordEncoderBeanShouldExist() {
        PasswordEncoder encoder = context.getBean(PasswordEncoder.class);
        assertThat(encoder).isNotNull();
    }

    @Test
    void daoAuthenticationProviderShouldSupportUsernamePasswordToken() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);
        assertThat(provider.supports(UsernamePasswordAuthenticationToken.class)).isTrue();
    }

    @Test
    void daoAuthenticationProviderShouldHaveCorrectDependenciesInjected() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        Object injectedUserDetailsService = ReflectionTestUtils.getField(provider, "userDetailsService");
        Object injectedPasswordEncoder = ReflectionTestUtils.getField(provider, "passwordEncoder");

        assertThat(injectedUserDetailsService).isSameAs(userDetailsService);
        assertThat(injectedPasswordEncoder).isSameAs(passwordEncoder);
    }

    @Test
    void securityFilterChainBeanShouldExist() {
        SecurityFilterChain chain = context.getBean(SecurityFilterChain.class);
        assertThat(chain).isNotNull();
    }

    @Test
    void authenticationManagerBeanShouldExist() {
        AuthenticationManager manager = context.getBean(AuthenticationManager.class);
        assertThat(manager).isNotNull();
    }

    @Test
    void userDetailsServiceBeanShouldExistAndBeTheCustomOne() {
        UserDetailsService service = context.getBean(UserDetailsService.class);
        assertThat(service).isNotNull();
        assertThat(service).isSameAs(userDetailsService);
    }

    @Test
    void daoAuthenticationProviderShouldSupportDummyCredentials() {
        DaoAuthenticationProvider provider = context.getBean(DaoAuthenticationProvider.class);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken("testuser", "testpassword");

        assertThat(provider.supports(token.getClass())).isTrue();
    }
    
    
    @TestConfiguration
    static class TestHealthConfig {
        @Bean
        public HealthIndicator mailHealthContributor() {
            return () -> Health.up().build();
        }
    }
}