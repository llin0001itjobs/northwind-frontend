package org.llin.demo.northwind;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public class TestDefaultProperties {

    @Autowired
    private PropertyDefaultProperties propertyDefaultProperties;

    @MockBean
    private JavaMailSender mailSender;   // ← prevents context failure + avoids real emails in tests

    @Test
    public void testDefaultPropertyValues() {
        assertNotNull(propertyDefaultProperties.getServer().getPort());
        assertNotNull(propertyDefaultProperties.getServer().getServlet().getContextPath());
        // Note: datasource properties are NOT present in the application.properties you showed.
        // If they come from application-dev.properties / Azure Key Vault / environment variables,
        // this will pass. Otherwise remove or adjust this assertion.
        // assertNotNull(propertyDefaultProperties.getSpring().getDatasource().getUsername());
        assertNotNull(propertyDefaultProperties.getManagement().getInfo().getEnv().getEnabled());
        assertNotNull(propertyDefaultProperties.getManagement().getEndpoints().getWeb());
    }
}