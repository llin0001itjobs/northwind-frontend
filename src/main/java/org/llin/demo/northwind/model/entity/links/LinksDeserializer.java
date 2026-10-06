package org.llin.demo.northwind.model.entity.links;

import java.io.IOException;

import org.llin.demo.northwind._JsonKeys;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LinksDeserializer extends JsonDeserializer<Links> implements _JsonKeys {
	
	@Override
	public Links deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
	    JsonNode node = jp.getCodec().readTree(jp);
	    Links links = new Links();
	    copyObject(node, "self", links);
	    copyAssociation(node, "company", links);
	    return links;
	}

	private void copyObject(JsonNode node, String rel, Links links) {
	    JsonNode relNode = node.get(rel);
	    if (relNode != null && relNode.isObject() && relNode.has("href")) {
	        put(links, rel, relNode.get("href").asText(), null);
	    }
	}

	private void copyAssociation(JsonNode node, String rel, Links links) {
	    JsonNode relNode = node.get(rel);
	    if (relNode == null || !relNode.isArray() || relNode.isEmpty()) {
	        return;
	    }
	    String href = null;
	    String id = null;
	    for (JsonNode item : relNode) {
	        if (!item.has("href")) {
	            continue;
	        }
	        String value = item.get("href").asText();
	        href = value;
	        String found = queryParam(value, "id");
	        if (found != null) {
	            id = found;
	        }
	    }
	    if (href != null) {
	        put(links, rel, href, id);
	    }
	}

	private void put(Links links, String rel, String href, String id) {
	    Link link = links.getLinks().computeIfAbsent(rel, key -> new Link(key));
	    link.setHref(href);
	    link.setId(id);
	    link.setLabel(rel);
	}

	private String queryParam(String href, String name) {
	    int q = href.indexOf('?');
	    if (q < 0) {
	        return null;
	    }
	    for (String part : href.substring(q + 1).split("&")) {
	        String[] pair = part.split("=", 2);
	        if (pair.length == 2 && pair[0].equals(name)) {
	            return pair[1];
	        }
	    }
	    return null;
	}
}	