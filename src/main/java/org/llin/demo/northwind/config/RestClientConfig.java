package org.llin.demo.northwind.config;

import java.nio.charset.StandardCharsets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

	@Bean
	RestClient northwindDataClient(RestClient.Builder builder, PropertyDefaultProperties props) { 
	    String baseUrl = props.getApp().getData().getApiUri();
	    if (!baseUrl.endsWith("/")) {
	        baseUrl += "/";
	    }
	    return builder
	        .baseUrl(baseUrl)
            .requestInterceptor((request, body, execution) -> {
                // --- DEBUG PRINT START ---
                System.out.println("=========================================");
                System.out.println("OUTBOUND HTTP METHOD: " + request.getMethod());
                System.out.println("OUTBOUND URI: " + request.getURI());
                System.out.println("OUTBOUND HEADERS: " + request.getHeaders());
                System.out.println("OUTBOUND PAYLOAD BODY: ");
                System.out.println(new String(body, StandardCharsets.UTF_8));
                System.out.println("=========================================");
                // --- DEBUG PRINT END ---

                return execution.execute(request, body);
            })	        
	        .build();
	}

}
