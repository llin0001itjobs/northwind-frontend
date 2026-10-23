package org.llin.demo.northwind.config;

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
                return execution.execute(request, body);
            })	        
	        .build();
	}

}
