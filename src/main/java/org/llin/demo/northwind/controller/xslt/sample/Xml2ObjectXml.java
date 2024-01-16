package org.llin.demo.northwind.controller.xslt.sample;

import java.io.InputStream;

import org.llin.demo.northwind.model.BaseObject;
import org.llin.demo.northwind.util.XsltTransformer;

public class Xml2ObjectXml<T extends BaseObject> {
	
	private static final String XML = "./TypeState.xml";
	private static final String XSL = "../TypeState.xslt";
	
	public Xml2ObjectXml() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		InputStream isXML = Xml2ObjectXml.class.getResourceAsStream(XML);
		InputStream isXSLT = Xml2ObjectXml.class.getResourceAsStream(XSL);
		XsltTransformer xsltTransformer = new XsltTransformer(isXSLT);

		String s = xsltTransformer.transform(isXML);
		System.out.println(s);
	}
	
}
