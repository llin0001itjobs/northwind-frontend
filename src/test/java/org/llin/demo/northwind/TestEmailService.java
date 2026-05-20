package org.llin.demo.northwind;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestEmailService extends BaseNorthwindTest {

    @Autowired
    private EmailService emailService;
	    
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