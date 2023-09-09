package org.llin.demo.northwind.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind.model.api.core.BaseObject;
import org.llin.demo.northwind.model.api.core.Link;
import org.llin.demo.northwind.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ModelViewCacheLoader<T extends BaseObject> {

	private static final String PATH_XSLT = "../controller/xslt/";
	private static final String PATH_JSON = "../controller/xslt/sample/";

	@Value("${api.package.northwind}")
	private String package_name;

	@Value("${api.package.northwind.excluded}")
	private String package_name_class_excluded;

	@Value("${client.api.northwind}")
	private String northwind_api;

	@Value("${api.usage}")
	private boolean withApi;

	private ModelViewCache<T> modelViewCache = new ModelViewCache<>();
	private ModelViewCacheConfig<T> mvCacheConfig = new ModelViewCacheConfig<>();
	
	public ModelViewCacheLoader() {
		// TODO Auto-generated constructor stub
	}

	public ModelViewCache<T> getModelViewCache() {
		return modelViewCache;
	}

	@PostConstruct
	public void loadObjects() throws IOException {		
		/*
		loadObjectsFromJson();
		populateChildrenFromLinks();
		*/
		modelViewCache.printOut();
	}

	private void loadObjectsFromJson() {
		String[] excluded = package_name_class_excluded.split(",");
		
		List<String> listOfNames = (List<String>) mvCacheConfig.getListOfTypeNames();
		for (String className : listOfNames) {
			if (Arrays.asList(excluded).contains(className))
				continue;
			try {
				JsonUtil<T> jsonUtil = new JsonUtil<>(northwind_api + "/" + className, PATH_JSON + "/" + className + ".json",
						PATH_XSLT + "/" + className + ".xslt", withApi);
				modelViewCache.add(className,
						jsonUtil.mapObjects(className, (Class<T[]>) mvCacheConfig.getMapOfTypes().get(className)));

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	
	@SuppressWarnings("unchecked")
	private void populateChildrenFromLinks() throws IOException {
		List<String> classNames = modelViewCache.getKeys();
		for (String className : classNames) {
			List<T> baseObjects = modelViewCache.get(className);
			for (T t : baseObjects) {
				List<T> children = new ArrayList<>();
				for (Link link : t.getLinks()) {
					JsonUtil<T> jsonUtil = new JsonUtil<>(link.getHref(), PATH_JSON + "/" + className + ".json",
							PATH_XSLT + "/" + className + ".xslt", withApi, true);
					children.addAll((List<T>)jsonUtil.mapObjects(className, (Class<T[]>) mvCacheConfig.getMapOfTypes().get(className)));					
				}
				t.setChildren((List<BaseObject>) children);
			}
		}
	}
	

}
