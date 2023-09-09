package org.llin.demo.northwind.controller.xslt.sample;

import java.io.InputStream;

import org.llin.demo.northwind.model.api.core.BaseObject;
import org.llin.demo.northwind.util.XsltTransformer;

public class Sample0001<T extends BaseObject> {

	private static final String XML = "./Company.xml";
	private static final String XSL = "../Company.xslt";
	
	public Sample0001() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		BaseObject[] baseObjects = {};
		InputStream isXML = Sample0001.class.getResourceAsStream(XML);
		InputStream isXSLT = Sample0001.class.getResourceAsStream(XSL);
		XsltTransformer xsltTransformer = new XsltTransformer(isXSLT);

		String s = xsltTransformer.transform(isXML);
		System.out.println(s);
	}
	
}
