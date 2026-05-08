package org.llin.demo.northwind.util.test;

import java.io.*;
import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.*;
import org.apache.fop.apps.*;

public class FOPExample {
	private static String SRC_PATH = "src/main/resources/static/xsl/";
	
    public static void main(String[] args) throws Exception {
    	
        // Step 1: Set up the XSLT transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(new File(SRC_PATH + "employee-roster.xsl")));

        // Step 2: Transform the XML to XSL-FO
        File xmlFile = new File(SRC_PATH + "employee-roster.xml");
        File xsltOutput = new File(SRC_PATH + "employee-roster.fo");
        transformer. setParameter("imageBasePath", "src/main/resources/static/images/");
        transformer.transform(new StreamSource(xmlFile), new StreamResult(xsltOutput));

        // Step 3: Set up FOP
        FopFactoryBuilder builder = new FopFactoryBuilder(new File(".").toURI());
        builder.setStrictFOValidation(false); // Disable strict validation
        FopFactory fopFactory = builder.build();
        OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(SRC_PATH + "employee-roster.pdf")));

        try {
            Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
            TransformerFactory fopTransformerFactory = TransformerFactory.newInstance();
            Transformer fopTransformer = fopTransformerFactory.newTransformer();
            Source foSource = new StreamSource(xsltOutput);
            Result foResult = new SAXResult(fop.getDefaultHandler());
            fopTransformer.transform(foSource, foResult);
        } finally {
            out.close();
        }
    }
}