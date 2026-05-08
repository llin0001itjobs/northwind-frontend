package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind.model.charts.LabelValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomSqlCache {
	private static final Logger logger = LoggerFactory.getLogger(CustomSqlCache.class);
	
	private Map<String, LabelValue[]> cacheObjectArray = new HashMap<>();
	
	/**
	 * Add to cache with key
	 * @param key
	 * @param baseObjects
	 */
	public void add(String key, LabelValue[] labelValues) {
		cacheObjectArray.put(key, labelValues);
	}

	public Map<String, LabelValue[]> getCacheObjectArray() {
		return cacheObjectArray;
	}

	/**
	 * Returns list of class name of type T in alphabetical order 
	 * @return
	 */
	public List<String> getObjectArrayKeys() {
		return new ArrayList<String>(new TreeSet<String>(cacheObjectArray.keySet()));
	}
	
	/**
	 * Only prints out when logger set to trace
	 * Will printOut with class name in alphabetical order
	 */
	public void printOutObjectArray() {
		LabelValue[] array;
		List<String> list = getObjectArrayKeys();
				
		for (String key : list) {		
			array = cacheObjectArray.get(key);
			logger.trace("{{" + key + "}}");
			System.out.println("{{" + key + "}}");
			for (LabelValue lv : array) {
				logger.trace("\t" + lv.toString());
				System.out.println("\t" + lv.toString());
			}
		}
	}
	
}
