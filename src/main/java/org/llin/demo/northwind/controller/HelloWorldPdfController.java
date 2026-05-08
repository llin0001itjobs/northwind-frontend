package org.llin.demo.northwind.controller;

import org.llin.demo.northwind.util.PdfRenderUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Response is dumped into ResponseEntity
 * which requires a return of byte[]
 */
@Controller
@RequestMapping("/test-pdf")
public class HelloWorldPdfController {
	
	@GetMapping("/hello-world")
	public ModelAndView showHelloWorld() {
		return new ModelAndView("test-pdf-hello-world");		
	}
    
	@GetMapping("/render-hello-world")
    public ResponseEntity<byte[]> render() throws Exception {

        // XML data for the PDF
        String xmlData = "<hello>World</hello>";
        
        byte[] ba = PdfRenderUtil.renderFromXml("xsl/hello-world.xsl", xmlData);


        // Prepare response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "hello-world.pdf");

        return new ResponseEntity<>(ba, headers, HttpStatus.OK);
    }
    
	@GetMapping("/employee-roster")
	public ModelAndView showEmployeeRoster() {
		return new ModelAndView("test-pdf-employee-roster");		
	}
	
	@GetMapping("/render-employee-roster")
    public ResponseEntity<byte[]> employeeRoster() throws Exception {
        
        byte[] ba = PdfRenderUtil.renderFromFile("xsl/employee-roster.xsl", "xsl/employee.xml");

        // Prepare response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "employee-roster.pdf");

        return new ResponseEntity<>(ba, headers, HttpStatus.OK);
    }    
}