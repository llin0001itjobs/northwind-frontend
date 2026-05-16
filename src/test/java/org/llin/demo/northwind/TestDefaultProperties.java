package org.llin.demo.northwind;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public class TestDefaultProperties {

	@Autowired
	private PropertyDefaultProperties propertyDefaultProperties = new PropertyDefaultProperties();

	@Test
	public void testDefaultPropertyValues() {
		assertNotNull(propertyDefaultProperties.getServer().getPort());
		assertNotNull(propertyDefaultProperties.getServer().getServlet().getContextPath());
		assertNotNull(propertyDefaultProperties.getSpring().getDatasource().getUsername());
		assertNotNull(propertyDefaultProperties.getManagement().getInfo().getEnv().getEnabled());
		assertNotNull(propertyDefaultProperties.getManagement().getEndpoints().getWeb());
	}
}