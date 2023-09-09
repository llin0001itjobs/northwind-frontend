package org.llin.demo.northwind.controller.xslt.sample;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Sample0000 {

	private static final String JSON = "./TypeState.json";
	
	public Sample0000() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {

		InputStream isJSON = Sample0000.class.getResourceAsStream(JSON);

		System.out.println(getXML(isJSON));
	}

	static String getXML(InputStream is) {		

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectMapper xmlMapper = new XmlMapper();

		String xml = "<<NOXML>>";
		 	
		try {
			JsonNode node = objectMapper.readTree(is);
			xml = xmlMapper.writeValueAsString(node);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		return xml;
				
	}
}
