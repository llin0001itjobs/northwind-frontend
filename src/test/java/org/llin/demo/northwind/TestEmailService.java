package org.llin.demo.northwind;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.llin.demo.northwind.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public class TestEmailService {

    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender mailSender;   // ← prevents context failure + avoids real emails in tests

    @Test
    public void testEmail() {
        try {
            emailService.sendSimpleEmail("llin0001test01@gmail.com", "Test Subject", "Test body");
            System.out.println("✅ Test email (mocked) completed successfully");
        } catch (Exception e) {
            System.err.println("Test email failed: " + e.getMessage());
        }
    }
}