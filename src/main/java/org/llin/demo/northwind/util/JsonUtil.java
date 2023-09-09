package org.llin.demo.northwind.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.llin.demo.northwind.Formats;
import org.llin.demo.northwind.cache.ModelViewCacheLoader;
import org.llin.demo.northwind.model.api.core.BaseObject;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class JsonUtil<T extends BaseObject> {
	
	private boolean _singleRandomly;
	
	private boolean _withApi;
	
	private String _hrefApi;
	
	private String _jsonFile;
	
	private String _xsltFile;
	
	public JsonUtil(String hrefApi, String jsonFile, String xsltFile, boolean withApi) {		
		this(hrefApi, jsonFile, xsltFile, withApi, false);	
	}
	
	public JsonUtil(String hrefApi, String jsonFile, String xsltFile, boolean withApi, boolean singleRandomly) {		
		_hrefApi = hrefApi;
		_jsonFile = jsonFile;
		_xsltFile = xsltFile;
		_withApi = withApi;
		_singleRandomly = singleRandomly;
	}
	
	

	public List<T> mapObjects(String className, Class<T[]> type) throws IOException {
		List<T> list = mapJsonText(getJsonText(className), className, type);
		List<T> modList = new LinkedList<>(list); 
		if (_singleRandomly) {
			int i = Double.valueOf((Math.floor(Math.random() * modList.size()))).intValue();
			T t = modList.get(i);
			modList.clear();
			modList.add(t);
		}
		return modList;
	}

	private String getJsonText(String className) throws IOException {
		InputStream is = null;
		String jsonText = null;
		JsonNode jsonNode = null;

		if (_withApi) {
			RestTemplate restTemplate = new RestTemplate();
			jsonText = restTemplate.getForObject(_hrefApi, String.class);
		} else {
			ObjectMapper objectMapper = new ObjectMapper();
			is = ModelViewCacheLoader.class.getResourceAsStream(_jsonFile);
			jsonNode = objectMapper.readTree(is); 
			jsonText = jsonNode.toString();
		}
		return jsonText;
	}

	@SuppressWarnings("unchecked")
	private List<T> mapJsonText(String jsonText, String className, Class<T[]> type) throws IOException {
		BaseObject[] baseObjects = {};
		String xml = "<<<NO_XML>>>";

		XmlMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlMapper.setDateFormat(new SimpleDateFormat(Formats.DATE_FORMAT));
		xmlMapper.findAndRegisterModules();

		XsltTransformer xsltTransformer = getTransformer(className);
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode node = objectMapper.readTree(jsonText.getBytes());
		xml = xmlMapper.writeValueAsString(node); 		 
		InputStream bis = new ByteArrayInputStream(xml.getBytes());
		xml = xsltTransformer.transform(bis);
		
		baseObjects = (T[]) xmlMapper.readValue(xml, type);

		if (bis != null)
			bis.close();

		return (List<T>) Arrays.asList(baseObjects); 
	}

	private XsltTransformer getTransformer(String className) {
		return new XsltTransformer(ModelViewCacheLoader.class.getResourceAsStream(_xsltFile));
	}
	
	@Override
	public String toString() {
		return "JsonUtil [_singleRandomly=" + _singleRandomly + ", _withApi=" + _withApi + ", _hrefApi=" + _hrefApi
				+ ", _jsonFile=" + _jsonFile + ", _xsltFile=" + _xsltFile + "]";
	}
	
}


