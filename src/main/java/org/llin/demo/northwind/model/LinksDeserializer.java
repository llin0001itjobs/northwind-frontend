package org.llin.demo.northwind.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

import org.llin.demo.northwind._JsonKeys;

public class LinksDeserializer extends JsonDeserializer<Links> implements _JsonKeys {

    @Override
    public Links deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        Links links = new Links();

        if (node.has(LINKS)) {
            JsonNode linksNode = node.get(LINKS);
            if (linksNode.has(SELF)) {
                JsonNode selfNode = linksNode.get(SELF);
                links.getLinks().get(SELF).setHref(selfNode.get(HREF).asText());
            }
        }
        return links;
    }
}
