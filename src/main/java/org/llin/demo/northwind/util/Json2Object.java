package org.llin.demo.northwind.util;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.llin.demo.northwind._JsonKeys;
import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.cache.ModelViewCache;
import org.llin.demo.northwind.cache.ModelViewCacheType;
import org.llin.demo.northwind.cache.RootPathType;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.model.BaseObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class Json2Object<T extends BaseObject> implements _JsonKeys, _Values {

	private static final String ORIGINAL_ROOT_PATH = "_embedded";
	
	@Autowired
	private RootPathType rootPathType;
	
	@Autowired
	ModelViewCache modelViewCache;

	@Autowired
	private ModelViewCacheType<T> modelViewCacheType;
		
	@Autowired
	private PropertiesConfig config;
	
	public T[] getObjects(String typeName, String jsonText)
			throws JsonMappingException, JsonProcessingException, ClassNotFoundException {
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node;
		node = objectMapper.convertValue(jsonText, JsonNode.class);		
		node = objectMapper.readTree(node.textValue());
		node = node.get(ORIGINAL_ROOT_PATH);
		node = node.get(rootPathType.getRootPathCache().get(typeName));		
		
		Class<T[]> type = modelViewCacheType.getMapOfTypes().get(typeName);
		T[] objects = objectMapper.convertValue(node, type);		
		if (node != null && node.isArray()) {  //will always be true
			int i = 0;
			for (JsonNode element : node) {
				JsonNode linksNode = element.get(LINKS);
				populateLinks(objects[i], linksNode, objectMapper);
				populateIdFromSelf(objects[i], typeName);
				i++; 
			}
		}

		return objects;
	}
		
	private void populateLinks(T object, JsonNode node, ObjectMapper objectMapper) {
		if (node != null) {
			Iterator<String> i = node.fieldNames();
			while (i.hasNext()) {
				String name = i.next();
				object.getLinks().addLink(name);
				JsonNode parent = node.get(name);
				if (parent.isArray()) {
					JsonNode firstChild = parent.get(0);
					((ObjectNode) node).set(name, firstChild);					
				}
				JsonNode href = node.get(name).get(HREF);
				object.getLinks().getLink(name).setHref(href.textValue());
				if (doesUriHaveId(href.textValue(), null)) {					
					object.getLinks().getLink(name).setId(getId(href.textValue(), null));				
				}
			}
		}
	}

	private void populateIdFromSelf(T object, String name) {		
		object.setId(StringUtil.populateIdFromLink(object.getLinks().getLink(SELF).getHref()));
	}
	
	private boolean doesUriHaveId(String uri, String regex) {
		boolean matches = false;
		regex = regex == null ? config.getRegexApiUri() : regex; 
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(uri);
		matches = matcher.find();
		return matches;
	}
	
	private String getId(String uri, String idParam) {
		String id = "";
		idParam = idParam == null ? config.getDataApiUriId() : idParam;
		id = uri.substring(uri.indexOf(idParam));
		id = id.substring(idParam.length());
		return id;
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String regex = "^http://localhost:8080\\/northwind-data\\/api\\/\\w+\\/\\d+\\/\\w+\\?id=(\\w+(?:,\\w+)*)$";
		String uri1 = "http://localhost:8080/northwind-data/api/customer/1/company?id=1";
		String uri2 = "http://localhost:8080/northwind-data/api/customer/1/company?id=1,3,5,6";
		String idParam = "id=";
		Json2Object jo = new Json2Object();
		System.out.println("Is it true: " + jo.doesUriHaveId(uri1, regex));
		System.out.println("ID is: " + jo.getId(uri1, idParam));
		System.out.println("Is it true: " + jo.doesUriHaveId(uri2, regex));
		System.out.println("ID is: " + jo.getId(uri2, idParam));		
	}
}
