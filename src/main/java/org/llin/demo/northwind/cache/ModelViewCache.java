package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind.model.BaseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ModelViewCache<T extends BaseObject> {

	private static final Logger logger = LoggerFactory.getLogger(ModelViewCache.class);
	
	private Map<String, List<T>> mvCacheSingle = new HashMap<>();
	
	private Map<String, List<T>> mvCacheMultiple = new HashMap<>();
		
	/**
	 * Add to cache with className as key
	 * @param className
	 * @param list
	 */
	public void addToMultiple(String className, List<T> list) {
		mvCacheMultiple.put(className, list);
	}

	/**
	 * Add to cache with className as key
	 * @param className
	 * @param list
	 */
	public void addToSingle(String className, List<T> list) {
		mvCacheSingle.put(className, list);
	}
	
	/**
	 * Gets list of T based on className
	 * @param className
	 * @return
	 */
	public List<T> getFromMultiple(String className) {
		return (List<T>) mvCacheMultiple.get(className);
	}
	
	/**
	 * Gets list of T based on className
	 * @param className
	 * @return
	 */
	public List<T> getFromSingle(String className) {
		return (List<T>) mvCacheSingle.get(className);
	}
	
	/**
	 * Returns list of class name of type T in alphabetical order 
	 * @return
	 */
	public List<String> getKeys() {
		return new ArrayList<String>(new TreeSet<String>(mvCacheMultiple.keySet()));
	}
	
	/**
	 * Only prints out when logger set to debug
	 * Will printOut with class name in alphabetical order
	 */
	public void printOut() {
		List<T> list;
		String key;
		Iterator<String> iter = mvCacheMultiple.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			list = mvCacheMultiple.get(key);
			logger.debug("{{" + key + "}}");
			for (BaseObject bo : list) {
				logger.debug("\t" + bo.toString());				
			}
		}
	}

}
