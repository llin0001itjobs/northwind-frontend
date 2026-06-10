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
import org.llin.demo.northwind.model.entity.util.RoleMapper;
import org.llin.demo.northwind.model.entity.util.UserMapper;
import org.llin.demo.northwind.service.entity.RoleService;
import org.llin.demo.northwind.service.entity.UserService;
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
	private RoleService roleService;

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

	    Optional<UserDto> optUserDto = userService.findByEmail(email);
	    Optional<RoleDto> optRoleDto;
	    User user;
	    List<Role> list = new ArrayList<>();
	    if (optUserDto.isEmpty()) {
	        user = new User();
	        user.setUsername(generateUsername(attributes, provider));
	        user.setEmail(email);
	        user.setPassword(UUID.randomUUID().toString());
	        user.setEnabled(true);
	        user.setEmailVerified(true);
	        user.setVerificationToken(UUID.randomUUID().toString());

	        // ←←← CRITICAL: safely get or create role
	        optRoleDto = roleService.findByRoleType("ROLE_USER");
	        if (optRoleDto.isEmpty()) {
	            throw new OAuth2AuthenticationException(
	                "Default role 'ROLE_USER' not found in database. " +
	                "Check that RoleSeeder has run or manually insert the role.");
	        }
	        list.add(RoleMapper.toEntity(optRoleDto.get()));
	        user.setRoles(list);

	        userService.update(user.getId(), UserMapper.toDto(user));
	    } else {
	        user = UserMapper.toEntity(optUserDto.get());
	    }

	    // Extra safety for any legacy users that might have null role
	    if (user.getRoles().isEmpty()) {
	    	optRoleDto = roleService.findByRoleType("ROLE_USER");
	        if (optRoleDto.isPresent()) {
	        	list.add(RoleMapper.toEntity(optRoleDto.get()));
	        	user.setRoles(list);
	        	userService.update(user.getId(), UserMapper.toDto(user));
	        } else {
	            throw new OAuth2AuthenticationException("Default role 'ROLE_USER' not found.");
	        }
	    }

	    OAuth2User oUser = new DefaultOAuth2User(
	            Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
	            attributes,
	            userNameAttribute);

	    return new CustomOAuth2User(oUser, user);
	}
	
	private String extractEmail(Map<String, Object> attributes, String provider) {
	    switch (provider.toLowerCase()) {
	        case "github":
	            // GitHub often returns null for email when it's set to private
	            String email = (String) attributes.get("email");
	            if (email == null || email.trim().isEmpty()) {
	                String login = (String) attributes.get("login");
	                if (login != null && !login.trim().isEmpty()) {
	                    email = login + "@github.com";   // unique placeholder email
	                }
	            }
	            return email;

	        case "google":
	            return (String) attributes.get("email");

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
	            return (login != null && !login.trim().isEmpty()) ? login : "user_" + UUID.randomUUID().toString().substring(0, 8);

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
