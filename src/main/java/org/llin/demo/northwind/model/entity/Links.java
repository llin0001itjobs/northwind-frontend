package org.llin.demo.northwind.model.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind._JsonKeys;
import org.springframework.stereotype.Component;

@Component
public class Links implements _Classes_EntityObject, _JsonKeys {
	
	private Map<String, Link> links = new HashMap<>();
	private Map<String, EntityObject> linkObjects = new HashMap<>();
	
	public Links() {
		super();
	}

    public void addLink(String linkName) {
        links.put(linkName, new Link(linkName));
    }

    public Link getLink(String linkName) {
        return links.get(linkName);
    }
    
    public List<String> getLinkNames() {
    	return new ArrayList<>(new TreeSet<>(links.keySet()));
    }
    
    public Map<String, Link> getLinks() {
        return links;
    }

    public void setLinks(Map<String, Link> links) {
        this.links = links;
    }
   
    public void addLinkObject(String linkName, EntityObject object) {
    	linkObjects.put(linkName, object);
    }
    
    public EntityObject getLinkObject(String linkName) {
        return linkObjects.get(linkName);
    }
        
    public List<String> getLinkObjectNames() {
    	return new ArrayList<>(new TreeSet<>(linkObjects.keySet()));
    }
        
	public Map<String, EntityObject> getLinkObjects() {
		return linkObjects;
	}

	public void setLinkObjects(Map<String, EntityObject> linkObjects) {
		this.linkObjects = linkObjects;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Links [links=");
		Iterator<String> i = links.keySet().iterator();
		while (i.hasNext()) {
			String k = i.next();
			sb.append(links.get(k).toString()); 
			sb.append(" ");
		}
		sb.append("]");
		return sb.toString();
	}
    	
    
}
