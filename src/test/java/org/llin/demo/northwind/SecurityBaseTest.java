package org.llin.demo.northwind;

import org.llin.demo.northwind.service.CustomOAuth2UserService;
import org.llin.demo.northwind.service.CustomUserDetailsService;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

public abstract class SecurityBaseTest extends BaseTest {

    @MockitoBean // Changed from @MockBean
    protected CustomUserDetailsService customUserDetailsService;

    @MockitoBean // Changed from @MockBean
    protected CustomOAuth2UserService customOAuth2UserService;
}
