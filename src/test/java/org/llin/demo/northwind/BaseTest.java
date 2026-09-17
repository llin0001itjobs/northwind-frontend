package org.llin.demo.northwind;

import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class BaseTest {

    @MockitoBean(name = "mailSender") // Changed from @MockBean
    protected JavaMailSender sharedMailMock;
}