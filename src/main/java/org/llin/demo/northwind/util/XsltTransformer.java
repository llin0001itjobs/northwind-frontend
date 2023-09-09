package org.llin.demo.northwind.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XsltTransformer {

	private InputStream xsltFile;

	public XsltTransformer(InputStream xsltFile) {
		this.xsltFile = xsltFile;
	}
	
	public String transform(InputStream is) {

		String xmlOuput = "";

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new InputSource(is));

			// transform xml to xml via a xslt file
			try (StringWriter writer = new StringWriter()) {
				
				transform(doc, writer);
				xmlOuput = writer.toString();
			}

		} catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
			e.printStackTrace();
		}
		return xmlOuput;
	}
	

	private void transform(Document doc, Writer output) throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();

		StreamSource ss = new StreamSource(this.xsltFile);
				
		// add XSLT in Transformer	
		Transformer transformer = transformerFactory.newTransformer(ss);

		transformer.transform(new DOMSource(doc), new StreamResult(output));
	}
	
}
