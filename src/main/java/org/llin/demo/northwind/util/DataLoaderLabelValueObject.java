package org.llin.demo.northwind.util;

import java.net.URI;
import java.net.URISyntaxException;

import jakarta.annotation.PostConstruct;

import org.llin.demo.northwind.cache.CustomSqlCache;
import org.llin.demo.northwind.cache.type.CustomSqlType;
import org.llin.demo.northwind.config.LoggingConfig;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.model.charts.LabelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;

@Component
public class DataLoaderLabelValueObject<T extends LabelValue> {

	private static final Logger logger = LoggerFactory.getLogger(DataLoaderLabelValueObject.class);

	@Autowired
	private LoggingConfig loggingConfig;

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private CustomSqlCache customSqlCache;
	
	@Autowired
	private CustomSqlType<T> customSqlType;
	
	@Autowired
	private Json2LabelValueObject<T> json2Object;

	@SuppressWarnings("unchecked")
	@PostConstruct
	@Scheduled(cron = "0 0 3 * * *")
	@Async
	public void loadFromApi() {
		String jsonText = "";
		T[] lv = null;
		try {
			
			for (String key : propertiesConfig.getCustomSqlKeys()) {				
				jsonText = DataFetcher.fetchDataFromApi(new URI(propertiesConfig.getDataApiUri() + key));
				lv = (T[]) ((CustomSqlType<T>)customSqlType.getMapOfType().get(key)).getLabelValue();
				lv = (T[]) json2Object.getCustomSqlObjects((T[]) lv, jsonText);
				customSqlCache.add(key, lv);
			}
			
			if (loggingConfig.getValue().equalsIgnoreCase("TRACE")) {
				customSqlCache.printOutObjectArray();
			}
		} catch (URISyntaxException | JsonProcessingException e) {
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
}
