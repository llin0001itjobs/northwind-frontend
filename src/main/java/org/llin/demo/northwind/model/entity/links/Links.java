package org.llin.demo.northwind.model.entity.links;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Links {
    private final Map<String, Link> links = new HashMap<>();

    public void add(String rel, Link link) {
        if (rel != null && link != null) {
            link.setLabel(rel);          // keep label in sync
            links.put(rel, link);
        }
    }

    public Link getLink(String rel) {
        return links.get(rel);
    }

    public Map<String, Link> getLinks() {
        return Collections.unmodifiableMap(links);
    }

    @Override
    public String toString() {
        return "Links" + links;
    }
}
