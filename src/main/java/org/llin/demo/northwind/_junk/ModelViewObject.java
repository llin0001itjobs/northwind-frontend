package org.llin.demo.northwind._junk;

import java.util.ArrayList;
import java.util.List;

public class ModelViewObject implements Comparable<ModelViewObject> {

	private String name;
	private String label;
	private boolean ignore = false;
	private boolean moreThanOne = false;
	
	private List<ModelViewObject> childObjects = new ArrayList<>();
	
	public ModelViewObject() {
		super();
	}

	public ModelViewObject(List<ModelViewObject> childObjects) {
		this.childObjects = childObjects;
	}
		
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean isIgnore() {
		return ignore;
	}

	public void setIgnore(boolean ignore) {
		this.ignore = ignore;
	}

	public boolean isMoreThanOne() {
		return moreThanOne;
	}

	public void setMoreThanOne(boolean moreThanOne) {
		this.moreThanOne = moreThanOne;
	}

	public List<ModelViewObject> getChildObjects() {
		return childObjects;
	}

	public void setChildObjects(List<ModelViewObject> childObjects) {
		this.childObjects = childObjects;
	}

	public void addChildObject(ModelViewObject childObject) {
		this.childObjects.add(childObject);
	}

	@Override
	public int compareTo(ModelViewObject o) {		
		return this.name.compareTo(o.name);
	}

	@Override
	public String toString() {
		return "ModelViewObject [name=" + name + ", label=" + label + ", ignore=" + ignore + ", moreThanOne="
				+ moreThanOne + ", childObjects=" + childObjects + "]";
	}
	
}
