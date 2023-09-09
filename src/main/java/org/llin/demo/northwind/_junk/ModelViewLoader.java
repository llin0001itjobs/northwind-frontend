package org.llin.demo.northwind._junk;

import java.util.ArrayList;
import java.util.List;

import org.llin.demo.northwind.model.api.core.BaseObject;

public class ModelViewLoader<T extends BaseObject>  {

	private List<ModelViewObject> coreBaseObjects = new ArrayList<>();

	public ModelViewLoader() {
		// TODO Auto-generated constructor stub
	}

	public List<ModelViewObject> getCoreBaseObjects() {
		return coreBaseObjects;
	}


	
}
