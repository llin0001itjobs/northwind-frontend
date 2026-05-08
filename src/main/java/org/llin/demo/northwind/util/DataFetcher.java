package org.llin.demo.northwind.util;

import java.net.URI;

import org.springframework.web.client.RestTemplate;

public class DataFetcher {

	public static String fetchDataFromApi(URI apiUrl) {
		RestTemplate restTemplate = new RestTemplate();
		String jsonText = restTemplate.getForObject(apiUrl, String.class);
		return jsonText;
	}
}
