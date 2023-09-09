package org.llin.demo.northwind.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind.model.api.core.BaseObject;
import org.springframework.stereotype.Component;

@Component
public class ModelViewCache<T extends BaseObject> {

	private Map<String, List<T>> map = new HashMap<>();
		
	public ModelViewCache() {
		// TODO Auto-generated constructor stub
	}

	public void add(String key, List<T> list) {
		map.put(key, list);
	}

	public List<T> get(String key) {
		return (List<T>) map.get(key);
	}
	
	public List<String> getKeys() {
		return new ArrayList<String>(new TreeSet<String>(map.keySet()));
	}
	
	public void printOut() {
		List<T> list;
		String key;
		Iterator<String> iter = map.keySet().iterator();
		while (iter.hasNext()) {
			key = iter.next();
			list = map.get(key);
			System.out.println("{{" + key + "}}");
			for (BaseObject bo : list) {
				System.out.println("\t" + bo.toString());				
			}
		}
	}

}
