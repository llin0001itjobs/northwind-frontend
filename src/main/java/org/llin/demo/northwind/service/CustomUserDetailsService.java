package org.llin.demo.northwind.service;

import java.util.List;
import java.util.stream.Collectors;
import org.llin.demo.northwind.dto.UserDto;
import org.llin.demo.northwind.service.entity.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Fetch user collection list matches from backend client mapping
        List<UserDto> list = userService.findByUsername(username);
        
        if (list == null || list.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        UserDto userDto = list.get(0);
        
        // 2. Build authorities ensuring it defaults safely to ROLE_USER if empty
        List<SimpleGrantedAuthority> authorities;
        
        if (userDto.roles() != null && !userDto.roles().isEmpty()) {
            authorities = userDto.roles().stream()
                .map(role -> {
                    String roleName = role.description().toUpperCase();
                    // Prepend standard Spring prefix convention if missing
                    if (!roleName.startsWith("ROLE_")) {
                        roleName = "ROLE_" + roleName;
                    }
                    return new SimpleGrantedAuthority(roleName);
                })
                .collect(Collectors.toList());
        } else {
            // Secure fallback authority: Never pass an empty or null collection to Spring Security
            authorities = List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // 3. Return authenticated domain instance context mapping
        return new org.springframework.security.core.userdetails.User(
            userDto.username(),
            userDto.password(),
            userDto.enabled(), // enabled status check verification mapping
            true,              // accountNonExpired
            true,              // credentialsNonExpired
            true,              // accountNonLocked
            authorities
        );
    }
}
