package org.llin.demo.northwind.util;

import org.llin.demo.northwind.model.Employee;
import org.llin.demo.northwind.model.EntityObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class ObjectToXmlUtil<T extends EntityObject> {

	public String getXML(T[] entityObjects) {
		// Create XmlMapper
		XmlMapper xmlMapper = new XmlMapper();

		String xml = "";
		try {
			xml = xmlMapper.writeValueAsString(entityObjects);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return xml;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) throws JsonProcessingException {
		ObjectToXmlUtil util = new ObjectToXmlUtil();
		Employee[] es = new Employee[2];
		Employee e1, e2;

		e1 = new Employee();
		e2 = new Employee();
		e1.setFirstName("Fred");
		e1.setLastName("F");
		e2.setFirstName("Larry");
		e2.setLastName("L");
		es[0] = e1;
		es[1] = e2;

		System.out.println(util.getXML(es));

	}
}
