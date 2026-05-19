package org.llin.demo.northwind;

import java.util.HashMap;

import org.llin.demo.northwind.config.PropertyDefaultProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import jakarta.servlet.ServletContext;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(PropertyDefaultProperties.class)
public class Application extends SpringBootServletInitializer {
	public static final HashMap<String, HashMap<Integer, String>> APPLICATION_MAP = new HashMap<>();

	@Autowired
	ApplicationContext applicationContext;

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@Override
	public void onStartup(ServletContext sc) {

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
}
