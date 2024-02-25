package org.llin.demo.northwind.cache;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind._Values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ApiCacheLoader implements _Values {

	private Map<String, String> _clientAPIs = new HashMap<>();
	
	@Autowired
	private Environment env;
	
	@PostConstruct
	public void init() {
		_clientAPIs.put(NORTHWIND_APIS, env.getProperty(NORTHWIND_APIS));
		_clientAPIs.put(COINBASE_APIS_CURRENCIES, env.getProperty(COINBASE_APIS_CURRENCIES));
		_clientAPIs.put(DOG_RANDOM_APIS, env.getProperty(DOG_RANDOM_APIS));
	}
}
