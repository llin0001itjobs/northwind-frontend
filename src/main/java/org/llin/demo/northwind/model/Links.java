package org.llin.demo.northwind.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.llin.demo.northwind._Classes;
import org.llin.demo.northwind._JsonKeys;
import org.springframework.stereotype.Component;

@Component
public class Links implements _Classes, _JsonKeys {
	
	private Map<String, Link> links = new HashMap<>();
	private Map<String, BaseObject> linkObjects = new HashMap<>();
	
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
   
    public void addLinkObject(String linkName, BaseObject object) {
    	linkObjects.put(linkName, object);
    }
    
    public BaseObject getLinkObject(String linkName) {
        return linkObjects.get(linkName);
    }
        
    public List<String> getLinkObjectNames() {
    	return new ArrayList<>(new TreeSet<>(linkObjects.keySet()));
    }
        
	public Map<String, BaseObject> getLinkObjects() {
		return linkObjects;
	}

	public void setLinkObjects(Map<String, BaseObject> linkObjects) {
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
