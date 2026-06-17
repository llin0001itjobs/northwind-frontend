package org.llin.demo.northwind;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TestEmailService extends BaseTest {

    @Autowired
    private EmailService emailService;           // ← Use the REAL service

    @Test
    void shouldSendSimpleEmail() {
        // When
        emailService.sendSimpleEmail(
            "llin0001test01@gmail.com", 
            "Test Subject", 
            "Test body"
        );

        // Then
        verify(sharedMailMock).send(any(org.springframework.mail.SimpleMailMessage.class));

        System.out.println("✅ Simple email test passed");
    }
}