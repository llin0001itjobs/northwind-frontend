package org.llin.demo.northwind.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.llin.demo.northwind.dto.RoleDto;
import org.llin.demo.northwind.dto.UserDto;
import org.llin.demo.northwind.model.entity.CustomOAuth2User;
import org.llin.demo.northwind.model.entity.Role;
import org.llin.demo.northwind.model.entity.User;
import org.llin.demo.northwind.service.entity.RoleService;
import org.llin.demo.northwind.service.entity.UserService;
import org.llin.demo.northwind.service.entity.mapper.RoleMapper;
import org.llin.demo.northwind.service.entity.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String userNameAttribute = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        Map<String, Object> attributes = oAuth2User.getAttributes();
        String provider = userRequest.getClientRegistration().getRegistrationId();

        String email = extractEmail(attributes, provider);
        if (email == null) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        List<UserDto> listUserDto = userService.findByEmail(email);
        Optional<RoleDto> optRoleDto;
        User user;
        List<Role> list = new ArrayList<>();

        if (listUserDto.isEmpty()) {
            // === NEW USER: create and persist properly ===
            user = new User();
            user.setUsername(generateUsername(attributes, provider));
            user.setEmail(email);
            user.setPassword(UUID.randomUUID().toString());
            user.setEnabled(true);
            user.setEmailVerified(true);
            user.setVerificationToken(UUID.randomUUID().toString());

            optRoleDto = roleService.findByRoleType("USER");
            if (optRoleDto.isEmpty()) {
                throw new OAuth2AuthenticationException(
                    "Default role 'USER' not found in database. " +
                    "Check that RoleSeeder has run or manually insert the role.");
            }
            list.add(roleMapper.toEntity(optRoleDto.get()));
            user.setRoles(list);

            // IMPORTANT FIX: use save/create instead of update on a non-persisted entity
            UserDto savedDto = userService.create(userMapper.toDto(user));  
            user = userMapper.toEntity(savedDto);

        } else {
            user = userMapper.toEntity(listUserDto.get(0));
        }

        // Extra safety for legacy users with null/empty roles
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            optRoleDto = roleService.findByRoleType("USER");
            if (optRoleDto.isPresent()) {
                list.clear();
                list.add(roleMapper.toEntity(optRoleDto.get()));
                user.setRoles(list);
                userService.update(user.getId(), userMapper.toDto(user));
            } else {
                throw new OAuth2AuthenticationException("Default role 'ROLE_USER' not found.");
            }
        }

        OAuth2User oUser = new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("USER")),
                attributes,
                userNameAttribute);

        return new CustomOAuth2User(oUser, user);
    }

    private String extractEmail(Map<String, Object> attributes, String provider) {
        switch (provider.toLowerCase()) {
            case "github":
                String email = (String) attributes.get("email");
                if (email == null || email.trim().isEmpty()) {
                    String login = (String) attributes.get("login");
                    if (login != null && !login.trim().isEmpty()) {
                        email = login + "@github.com";
                    }
                }
                return email;

            case "google":
            case "facebook":
                return (String) attributes.get("email");

            default:
                return null;
        }
    }

    private String generateUsername(Map<String, Object> attributes, String provider) {
        switch (provider.toLowerCase()) {
            case "github":
                String login = (String) attributes.get("login");
                return (login != null && !login.trim().isEmpty()) 
                        ? login 
                        : "user_" + UUID.randomUUID().toString().substring(0, 8);

            case "google":
                String email = (String) attributes.get("email");
                if (email != null && !email.trim().isEmpty()) {
                    return email.split("@")[0];
                }
                break;

            case "facebook":
                Object nameObj = attributes.get("name");
                if (nameObj != null) {
                    return nameObj.toString().toLowerCase().replaceAll("\\s+", ".");
                }
                break;

            default:
                break;
        }
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }
}