package org.llin.demo.northwind.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.FOPException;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.core.io.ClassPathResource;

public class PdfRenderUtil {

	public static byte[] renderFromXml(String xslPath, String xmlData)
			throws IOException, FOPException, TransformerException {

		// Load XSL-FO template
		ClassPathResource xslResource = new ClassPathResource(xslPath);

		InputStream xslInputStream = xslResource.getInputStream();

		// FOP setup
		FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);

		// XSLT transformation
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(new StreamSource(xslInputStream));
		transformer.transform(new StreamSource(new StringReader(xmlData)), new SAXResult(fop.getDefaultHandler()));

		return out.toByteArray();
	}

	public static byte[] renderFromFile(String xslPath, String xmlPath)
			throws IOException, FOPException, TransformerException {
		String xmlData = "";
		// Load XML data
		ClassPathResource xmlResource = new ClassPathResource(xmlPath);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(xmlResource.getInputStream()))) {
			xmlData = reader.lines().collect(Collectors.joining("\n"));

		}
		return renderFromXml(xslPath, xmlData);
	}

	/**
	 * Uses xslPath to transform xmlInputStream resulting output stream will be in
	 * same path as xslPath
	 * 
	 * @param xslPath
	 * @param xmlInputStream
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws TransformerException 
	 * @throws FOPException 
	 * @throws Exception
	 */

	public static byte[] renderFoToPdf(String xslPath, InputStream xmlInputStream) throws FileNotFoundException, IOException, TransformerException, FOPException {
		byte[] byteArray = null; 
		String pdfPath = xslPath.replace(".xsl", ".pdf");
		String foPath = xslPath.replace(".xsl", ".fo");

		// Step 1: Transform XML to XSL-FO and save to file
		try (FileOutputStream foOut = new FileOutputStream(foPath);
				FileInputStream xslInputStream = new FileInputStream(xslPath)) {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(new StreamSource(xslInputStream));
			transformer.transform(new StreamSource(xmlInputStream), new StreamResult(foOut));
		}

		// Step 2: Process XSL-FO to PDF
		try (FileOutputStream pdfOut = new FileOutputStream(pdfPath);
				FileInputStream foInputStream = new FileInputStream(foPath)) {
			FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, pdfOut);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer(); // Identity transformer for FO
			transformer.transform(new StreamSource(foInputStream), new SAXResult(fop.getDefaultHandler()));
	        // Read the file directly into a byte array
	        byteArray = Files.readAllBytes(Paths.get(pdfPath));
		}
		return byteArray;
	}

	public static void main(String[] args) {
		String xslPath = "src/main/resources/static/xsl/employee-roster.xsl";
		String xmlPath = "static/xsl/employee-roster.xml";
		try {
			// Load XML data
			ClassPathResource xmlResource = new ClassPathResource(xmlPath);
			InputStream xmlInputStream = xmlResource.getInputStream();
			renderFoToPdf(xslPath, xmlInputStream);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
