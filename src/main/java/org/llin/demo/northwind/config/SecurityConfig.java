package org.llin.demo.northwind.config;

import org.llin.demo.northwind.service.CustomOAuth2UserService;
import org.llin.demo.northwind.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http,
	                                                   BCryptPasswordEncoder passwordEncoder,
	                                                   CustomUserDetailsService userDetailsService) throws Exception {
	    return http.getSharedObject(AuthenticationManagerBuilder.class)
	            .userDetailsService(userDetailsService)
	            .passwordEncoder(passwordEncoder)
	            .and()
	            .build();
	}

	// Define the authentication provider using DAO (JPA or any user store)
	@Bean
	public DaoAuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder);
		return authProvider;
	}

	// Configure HTTP security
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, CustomUserDetailsService userDetailsService,
			CustomOAuth2UserService oAuth2UserService) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/", "/login", "/setPassword", "/verify",
								"/resources/**", "/css/**",
								"/js/**", "/images/**")
						.permitAll().requestMatchers("/user/**").authenticated().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/home", true).permitAll())
				.oauth2Login(oauth -> oauth.loginPage("/login").defaultSuccessUrl("/home", true)
						.userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService)))
				.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
						.clearAuthentication(true));

		return http.build();
	}
}