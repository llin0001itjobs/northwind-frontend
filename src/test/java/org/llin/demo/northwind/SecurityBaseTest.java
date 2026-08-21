package org.llin.demo.northwind;

import org.llin.demo.northwind.service.CustomOAuth2UserService;
import org.llin.demo.northwind.service.CustomUserDetailsService;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class SecurityBaseTest extends BaseTest {
    
    @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @MockBean
    protected CustomOAuth2UserService customOAuth2UserService;
}