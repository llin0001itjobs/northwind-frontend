package org.llin.demo.northwind.util;

import static org.llin.demo.northwind.util.StringUtil.deCapitalize;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._JsonKeys;
import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.ModelViewCache;
import org.llin.demo.northwind.cache.ModelViewCacheType;
import org.llin.demo.northwind.config.LoggingConfig;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.model.BaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;


@Component
public class DataLoader<T extends BaseObject> implements _Classes, _JsonKeys, _Values {

	private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

	@Autowired
	private LoggingConfig loggingConfig;
	
	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private Json2Object<T> json2Object;

	@Autowired
	ModelViewCache modelViewCache;

	@Autowired
	private ModelViewCacheType<T> modelViewCacheType;

	private T[] otherEmployees = null;
	
	@PostConstruct
	@Scheduled(cron = "0 0 3 * * *")
	@Async
	public void loadFromApi() {
		List<String> listOfKey = modelViewCacheType.getKeyOfTypes();
		String jsonText = null;
		T[] objects = null;
		try {
			for (String key : listOfKey) {
				jsonText = DataFetcher.fetchDataFromApi(new URI(propertiesConfig.getDataApiUri() + deCapitalize(key)));				
				objects = json2Object.getObjects(key, jsonText);
				if (key.equals(EMPLOYEE)) {
					otherEmployees = objects;
				}
				modelViewCache.add(key, objects);
			}
			modelViewCache.add(APPROVED_BY, otherEmployees);
			modelViewCache.add(CREATED_BY, otherEmployees);
			modelViewCache.add(SUBMITTED_BY, otherEmployees);			   	
			modelViewCache.distributeProductsPerSupplier();
			modelViewCache.distributeSuppliersPerProduct();
	    	if (loggingConfig.getValue().equalsIgnoreCase("TRACE")) {    		
	    		modelViewCache.printOutObjectArray();	
	    	} 			
		} catch (URISyntaxException | JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
}
