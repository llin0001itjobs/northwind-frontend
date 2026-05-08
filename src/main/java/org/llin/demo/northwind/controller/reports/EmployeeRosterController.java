package org.llin.demo.northwind.controller.reports;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind.cache.EntityObjectCache;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.model.Employee;
import org.llin.demo.northwind.model.EntityObject;
import org.llin.demo.northwind.util.ObjectToXmlUtil;
import org.llin.demo.northwind.util.PdfRenderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/employee-roster")
public class EmployeeRosterController<T extends EntityObject> implements _Classes_EntityObject {


	private static final Logger logger = LoggerFactory.getLogger(EmployeeRosterController.class);

	private static final String PDF_FILE = "employee-roster.pdf";

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private PropertiesConfig propertiesConfig;

	@Autowired
	private EntityObjectCache<T> entityObjectCache;

	@GetMapping("/show")
	public ModelAndView showRoster() {
		ModelAndView mv = new ModelAndView("reports/employee-roster");
		chunk(mv);
		return mv;
	}

	@GetMapping("/preview")
	public ModelAndView previewRoster() {
		ModelAndView mv = new ModelAndView("reports/employee-roster-preview");
		chunk(mv);
		return mv;
	}

	@GetMapping("/render")
	@PreAuthorize("isAuthenticated()")
    public ResponseEntity<byte[]> render(@RequestParam(defaultValue = "attachment") String disposition) {
        try {
            ObjectToXmlUtil<Employee> objectToXmlUtil = new ObjectToXmlUtil<>();
            Employee[] employees = (Employee[]) entityObjectCache.getObjectArray(EMPLOYEE);
            if (employees == null || employees.length == 0) {
                logger.warn("No employees found in cache for rendering PDF");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
            String xmlData = objectToXmlUtil.getXML(employees);
            Resource xslResource = resourceLoader.getResource("classpath:static/xsl/employee-roster.xsl");
            if (!xslResource.exists()) {
                logger.error("XSL file not found: static/xsl/employee-roster.xsl");
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            byte[] ba = PdfRenderUtil.renderFromXml(xslResource.getFile().getPath(), xmlData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            if ("inline".equals(disposition)) {
                headers.setContentDispositionFormData("inline", PDF_FILE);
            } else {
                headers.setContentDispositionFormData("attachment", PDF_FILE);
            }

            return new ResponseEntity<>(ba, headers, HttpStatus.OK);
        } catch (IOException e) {
            logger.error("Failed to render PDF due to I/O error", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            logger.error("Failed to render PDF", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

	private void chunk(ModelAndView mv) {
	    Employee[] employeesArray = (Employee[]) entityObjectCache.getObjectArray(EMPLOYEE);
	    if (employeesArray == null) {
	        employeesArray = new Employee[0];
	    }
	    List<Employee> employees = Arrays.asList(employeesArray);
	    int chunkSize = propertiesConfig.getEmployeeChunkSize().intValue();
	    List<List<Employee>> chunkedEmployees = IntStream.range(0, (employees.size() + chunkSize - 1) / chunkSize)
	            .mapToObj(i -> employees.subList(i * chunkSize, Math.min((i + 1) * chunkSize, employees.size())))
	            .collect(Collectors.toList());

	    mv.addObject("employees", chunkedEmployees);
	    mv.addObject("chunkSize", chunkSize);
	}

}
