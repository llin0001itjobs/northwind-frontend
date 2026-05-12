package org.llin.demo.northwind.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class BeanConfig {

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        return builder
                // ←←← Change this to the real base URL of your API
                .baseUrl("http://localhost:8080")   // example – adjust as needed
                .build();
    }
}