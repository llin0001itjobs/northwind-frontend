package org.llin.demo.northwind;

import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NorthwindApplication extends SpringBootServletInitializer {
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
		return application.sources(NorthwindApplication.class);

	}

	public static void main(String[] args) {
		SpringApplication.run(NorthwindApplication.class, args);
	}
	
}
