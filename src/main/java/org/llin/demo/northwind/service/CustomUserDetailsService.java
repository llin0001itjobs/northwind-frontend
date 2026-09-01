package org.llin.demo.northwind.service;

import java.util.Collections;
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
        // Load user from the database
        List<UserDto> list = userService.findByUsername(username);
        
        if (list.isEmpty()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        
        UserDto userDto = list.get(0);
        
        // Build authorities (never null!)
        List<SimpleGrantedAuthority> authorities = Collections.emptyList();

        // Optional enhancement: load roles if your UserDto (or related data) contains them

        if (userDto.roles() != null) {
            authorities = userDto.roles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.type()))
                    .collect(Collectors.toList());
        }

        // Return a Spring Security User with username, password, and authorities
        return new org.springframework.security.core.userdetails.User(
                userDto.username(),
                userDto.password(),
                authorities   // ← fixed: never null
        );
    }
}