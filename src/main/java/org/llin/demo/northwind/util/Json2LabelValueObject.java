package org.llin.demo.northwind.util;

import java.io.IOException;

import org.llin.demo.northwind._JsonKeys;
import org.llin.demo.northwind._Values;
import org.llin.demo.northwind.model.analytics.LabelValue;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Json2LabelValueObject<T extends LabelValue> implements _JsonKeys, _Values {

	@SuppressWarnings("unchecked")
	public T[] getCustomSqlObjects(T[] labelValue, String jsonText)
			throws JsonMappingException, JsonProcessingException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		T[] result = null;
		
		try {
			result = (T[]) mapper.readValue(jsonText, labelValue.getClass());
	
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
	
}
