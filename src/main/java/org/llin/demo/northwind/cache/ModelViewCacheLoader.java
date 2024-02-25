package org.llin.demo.northwind.cache;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.model.Link;
import org.llin.demo.northwind.util.JsonUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ModelViewCacheLoader<T extends BaseObject> {

	private static final String PATH_XSLT = "../controller/xslt";

	@Value("${api.package.northwind}")
	private String package_name;

	@Value("${api.package.northwind.excluded}")
	private String package_name_class_excluded = "ProductSupplier,User";

	@Value("${api.northwind-data.uri}")
	private String northwind_data_api;

	private ModelViewCache<T> modelViewCache = new ModelViewCache<>();
	private ModelViewCacheConfig<T> mvCacheConfig = new ModelViewCacheConfig<>();
	
	public ModelViewCacheLoader() {
		// TODO Auto-generated constructor stub
	}

	public ModelViewCache<T> getModelViewCache() {
		return modelViewCache;
	}

	@PostConstruct
	@Scheduled(cron = "0 0 3 * * ?") //every day at 3am		
	public void loadObjects() throws IOException {		
		loadObjectsFromJson();
		populateChildrenFromLinks();
		modelViewCache.printOut();
	}

	private void loadObjectsFromJson() {
		
		String[] excluded = package_name_class_excluded.split(",");
		
		List<String> listOfNames = (List<String>) mvCacheConfig.getListOfTypeNames();
		
		for (String className : listOfNames) {
			if (Arrays.asList(excluded).contains(className))
				continue;
			try {
				JsonUtil<T> jsonUtil = new JsonUtil<>(northwind_data_api + "/" + className.substring(0,1).toLowerCase() + className.substring(1),
													  PATH_XSLT + "/" + className + ".xslt",
													  null);
				modelViewCache.addToMultiple(className,
						jsonUtil.mapObjects(mvCacheConfig.getMapOfTypes().get(className)));

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
			List<T> baseObjects = modelViewCache.getFromMultiple(className);
			for (T t : baseObjects) {
				System.out.println(t.toString());				
				List<T> children = new ArrayList<>();
				for (Link link : t.getLinks()) {  
					System.out.println("link.getHref() " + link.getHref());
					String xsltName = link.getHref().substring(link.getHref().lastIndexOf("/") + 1);
					String linkClassName = xsltName.substring(0, 1).toUpperCase() + xsltName.substring(1);
					JsonUtil<T> jsonUtil = new JsonUtil<>(link.getHref(),
							PATH_XSLT + "/" + linkClassName + ".xslt",							
							null);
					children.addAll((List<T>)jsonUtil.mapObjects((Class<T[]>) mvCacheConfig.getMapOfTypes().get(linkClassName)));					
				}
				setIdFromLink(children);
				t.setChildren((List<BaseObject>) children);
			}
		}
	}
	
	
	/*
	 * 
	 */
	private void setIdFromLink(List<T> list) {
		for (BaseObject bo : list) {
			String href = bo.getLinks().get(0).getHref();
			int index = href.lastIndexOf("/");
			bo.setId(href.substring(index+1));
		}		
	}

}
