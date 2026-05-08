package org.llin.demo.northwind.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.data.entity.Role;
import org.llin.demo.northwind.data.entity.UserDetail;
import org.llin.demo.northwind.data.repository.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

class CustomUserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists_ReturnsUserDetails() {
        UserDetail user = new UserDetail();
        user.setUsername("testuser");
        user.setPassword("encryptedPassword");
        user.setEnabled(true);
        user.setRoles(new ArrayList<Role>(Set.of(new Role("ROLE_USER","Default User, least privilege."))));

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        UserDetails details = userDetailsService.loadUserByUsername("testuser");

        assertThat(details).isNotNull();
        assertThat(details.getUsername()).isEqualTo("testuser");
        assertThat(details.getPassword()).isEqualTo("encryptedPassword");
        assertThat(details.getAuthorities()).extracting("authority").contains("ROLE_USER");
    }

    @Test
    void loadUserByUsername_UserNotFound_ThrowsException() {
        when(userRepository.findByUsername("missinguser")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsService.loadUserByUsername("missinguser");
        });
    }
}
