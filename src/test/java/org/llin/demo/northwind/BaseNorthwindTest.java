package org.llin.demo.northwind;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootTest
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public abstract class BaseNorthwindTest {

    @MockBean
    protected JavaMailSender mailSender;   // ← declared once for ALL tests
}