package org.llin.demo.northwind;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

public abstract class BaseTest {
	
    @MockBean(name = "mailSender")
    protected JavaMailSender sharedMailMock;
}
