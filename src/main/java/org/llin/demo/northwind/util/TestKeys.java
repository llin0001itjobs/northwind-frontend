package org.llin.demo.northwind.util;

import java.io.InputStream;
import java.security.KeyStore;
import java.util.Enumeration;

public class TestKeys {

	public static void main(String[] args) {
		System.setProperty("javax.net.ssl.trustStore", "classpath:keys/client-truststore.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "xxxxxx");
		
		try (InputStream is = ClassLoader.getSystemResourceAsStream("keys/client-truststore.jks")) {
		    KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		    trustStore.load(is, "xxxxxx".toCharArray());

		    Enumeration<String> aliases = trustStore.aliases();
		    while (aliases.hasMoreElements()) {
		        String alias = aliases.nextElement();
		        System.out.println("Alias: " + alias);
		    }
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	}
}
