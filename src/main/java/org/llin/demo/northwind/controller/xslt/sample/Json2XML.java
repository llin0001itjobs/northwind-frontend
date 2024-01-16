package org.llin.demo.northwind.controller.xslt.sample;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class Json2XML {

	private static final String JSON = "./PurchaseOrderDetail.json";
	
	public Json2XML() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {

		InputStream isJSON = Json2XML.class.getResourceAsStream(JSON);

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
