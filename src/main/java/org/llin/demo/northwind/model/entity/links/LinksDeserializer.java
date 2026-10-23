package org.llin.demo.northwind.model.entity.links;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class LinksDeserializer extends JsonDeserializer<Links> {
    @Override
    public Links deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        Links links = new Links();
        if (node != null && node.isObject()) {
            node.fields().forEachRemaining(entry -> {
                String rel = entry.getKey();
                JsonNode linkNode = entry.getValue();

                if (linkNode.isObject() && linkNode.has("href")) {
                    Link link = new Link(rel);
                    link.setHref(linkNode.get("href").asText());
                    // optional id extraction
                    String href = link.getHref();
                    String id = queryParam(href, "id");
                    if (id != null) link.setId(id);
                    links.add(rel, link);
                }
                // handle array-style links (e.g. "company": [ {...}, {...} ])
                else if (linkNode.isArray() && !linkNode.isEmpty()) {
                    JsonNode first = linkNode.get(0);
                    if (first != null && first.has("href")) {
                        Link link = new Link(rel);
                        link.setHref(first.get("href").asText());
                        links.add(rel, link);
                    }
                }
            });
        }
        return links;
    }

    private String queryParam(String href, String name) {
        if (href == null) return null;
        int q = href.indexOf('?');
        if (q < 0) return null;
        for (String part : href.substring(q + 1).split("&")) {
            String[] pair = part.split("=", 2);
            if (pair.length == 2 && pair[0].equals(name)) return pair[1];
        }
        return null;
    }
}	