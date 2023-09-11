package org.llin.demo.northwind.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.llin.demo.northwind.Formats;
import org.llin.demo.northwind.model.api.core.BaseObject;
import org.llin.demo.northwind.util.XsltTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public abstract class BaseController<T extends BaseObject> implements _Values {

	public static final String NORTHWIND_APIS = "client.api.northwind";
	public static final String COINBASE_APIS_CURRENCIES = "client.api.coinbase.currencies";
	public static final String DOG_RANDOM_APIS = "client.api.dogs.images.random";
	public static final String ENTITIES_PAGE = "entities";
	public static final String ENTITIES = "ENTITIES";
	public static final String TITLE = "TITLE";	
	
	private static final Logger logger = LogManager.getLogger(BaseController.class);

	private RestTemplate _restTemplate;

	Map<String, String> _clientAPIs = new HashMap<>();
	Class<T[]> _type;
	XsltTransformer _xsltTransformer;
	Reader _jsonReader;
	List<String> _configList;

	@Value("${view.ellipsis.limit}")
	private int ellipsisLimit;
	
	@Value("${api.usage}")
	private boolean withApi;
	@Autowired
	private Environment env;

	BaseController() {
		_configList = new ArrayList<>();
		loadConfigList();
	}

	abstract void loadConfigList();

	@PostConstruct
	public void init() {
		_clientAPIs.put(NORTHWIND_APIS, env.getProperty(NORTHWIND_APIS));
		_clientAPIs.put(COINBASE_APIS_CURRENCIES, env.getProperty(COINBASE_APIS_CURRENCIES));
		_clientAPIs.put(DOG_RANDOM_APIS, env.getProperty(DOG_RANDOM_APIS));
		_restTemplate = new RestTemplate();
	}

	List<T> getAllObjects(String objectName) {		

		List<T> list = new ArrayList<>();
		try {
			String jsonText = getJsonText(objectName);
			list = mapJsonText(jsonText);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}

		return list;
	}

	private String getJsonText(String objectName) throws IOException {
		String jsonText = null;
		
		if (withApi) {
			jsonText = _restTemplate.getForObject(_clientAPIs.get(NORTHWIND_APIS) + "/" + objectName, String.class);			
		} else {
			jsonText = (new ObjectMapper()).createParser(_jsonReader).getText();			
		}
		return jsonText;
	}
	
	@SuppressWarnings("unchecked")
	private List<T> mapJsonText(String jsonText) throws IOException {
		BaseObject[] baseObjects = {};
		String xml = "<<<NO_XML>>>";
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectMapper xmlMapper = new XmlMapper();
		xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		xmlMapper.setDateFormat(new SimpleDateFormat(Formats.DATE_FORMAT));
		xmlMapper.findAndRegisterModules();

		JsonNode node;
		InputStream bis = null;

		try {
			node = objectMapper.readTree(jsonText.getBytes());
			xml = xmlMapper.writeValueAsString(node);
			bis = new ByteArrayInputStream(xml.getBytes());
			xml = _xsltTransformer.transform(bis);
			baseObjects = (T[]) xmlMapper.readValue(xml, _type);
		} catch (IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
		}
		return (List<T>) Arrays.asList(baseObjects);
	}

	private void populateFromHref(List<T> list) {
		
	}
	
}
