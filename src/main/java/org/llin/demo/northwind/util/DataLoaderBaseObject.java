package org.llin.demo.northwind.util;

import static org.llin.demo.northwind.util.StringUtil.deCapitalize;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._JsonKeys;
import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.EntityObjectCache;
import org.llin.demo.northwind.cache.type.BaseObjectCacheType;
import org.llin.demo.northwind.config.LoggingConfig;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.model.EntityObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class DataLoaderBaseObject<T extends EntityObject> implements _Classes_EntityObject, _JsonKeys, _Values {

	private static final Logger logger = LoggerFactory.getLogger(DataLoaderBaseObject.class);

	@Autowired
	private LoggingConfig loggingConfig;

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private Json2BaseObject<T> json2Object;

	@Autowired
	private EntityObjectCache<T> baseObjectCache;

	@Autowired
	private BaseObjectCacheType<T> baseObjectCacheType;

	private T[] otherEmployees = null;

	@PostConstruct
	@Scheduled(cron = "0 0 3 * * *")
	@Async
	public void loadFromApi() {
		List<String> listOfKey = baseObjectCacheType.getKeyOfBaseType();
		String jsonText = "";
		T[] objects = null;

		try {
			for (String key : listOfKey) {
				jsonText = DataFetcher.fetchDataFromApi(new URI(propertiesConfig.getDataApiUri() + deCapitalize(key)));
				objects = json2Object.getObjects(key, jsonText);
				if (key.equals(EMPLOYEE)) {
					otherEmployees = objects;
				}
				baseObjectCache.add(key, objects);
			}
			baseObjectCache.add(APPROVED_BY, otherEmployees);
			baseObjectCache.add(CREATED_BY, otherEmployees);
			baseObjectCache.add(SUBMITTED_BY, otherEmployees);
			baseObjectCache.distributeProductsPerSupplier();
			baseObjectCache.distributeSuppliersPerProduct();
						
			if (loggingConfig.getValue().equalsIgnoreCase("TRACE")) {
				baseObjectCache.printOutObjectArray();
			}
		} catch (URISyntaxException | JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
}
