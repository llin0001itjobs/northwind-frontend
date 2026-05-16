package org.llin.demo.northwind.controller.reports;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.llin.demo.northwind._Classes_EntityObject;
import org.llin.demo.northwind.config.PropertiesConfig;
import org.llin.demo.northwind.dto.EmployeeDto;          // ← new import
import org.llin.demo.northwind.model.entity.Employee;
import org.llin.demo.northwind.model.entity.EntityObject;
import org.llin.demo.northwind.service.entity.EmployeeService; // ← new import
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
    private static final String XSL_PATH = "static/xsl/employee-roster.xsl";

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private PropertiesConfig propertiesConfig;

    @Autowired
    private EmployeeService employeeService;          // ← injected

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
            Employee[] employees = loadEmployees();

            if (employees == null || employees.length == 0) {
                logger.warn("No employees found for PDF rendering");
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            Resource xslResource = resourceLoader.getResource("classpath:" + XSL_PATH);
            if (!xslResource.exists()) {
                logger.error("XSL template not found: {}", XSL_PATH);
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String xmlData = new ObjectToXmlUtil<Employee>().getXML(employees);
            byte[] pdfBytes = PdfRenderUtil.renderFromXml(XSL_PATH, xmlData);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            if ("inline".equals(disposition)) {
                headers.setContentDispositionFormData("inline", PDF_FILE);
            } else {
                headers.setContentDispositionFormData("attachment", PDF_FILE);
            }

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Failed to render employee roster PDF", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void chunk(ModelAndView mv) {
        Employee[] employeesArray = loadEmployees();

        List<Employee> employees = (employeesArray == null)
                ? List.of()
                : Arrays.asList(employeesArray);

        int chunkSize = propertiesConfig.getEmployeeChunkSize().intValue();

        List<List<Employee>> chunkedEmployees = IntStream.range(0, (employees.size() + chunkSize - 1) / chunkSize)
                .mapToObj(i -> employees.subList(i * chunkSize, Math.min((i + 1) * chunkSize, employees.size())))
                .collect(Collectors.toList());

        mv.addObject("employees", chunkedEmployees);
        mv.addObject("chunkSize", chunkSize);
    }

    /**
     * Loads employees from the REST service and converts DTO → Entity
     * (so your existing XML/PDF logic continues to work unchanged).
     */
    private Employee[] loadEmployees() {
        List<EmployeeDto> dtos = employeeService.findAll();

        logger.debug("Loaded {} employees from REST service for roster report", dtos.size());

        return dtos.stream()
                .map(this::convertToEmployee)
                .toArray(Employee[]::new);
    }

    /**
     * DTO → Entity mapper.
     * Add any missing fields from your Employee / EmployeeDto classes here.
     */
    private Employee convertToEmployee(EmployeeDto dto) {
        if (dto == null) return null;

        Employee e = new Employee();
        e.setId(dto.id());                    // assuming standard Northwind fields
        e.setFirstName(dto.firstName());
        e.setLastName(dto.lastName());
        e.setJobTitle(dto.jobTitle());
        // e.setTitleOfCourtesy(dto.getTitleOfCourtesy());
        // e.setBirthDate(dto.getBirthDate());
        // e.setHireDate(dto.getHireDate());
        // e.setAddress(dto.getAddress());
        // e.setCity(dto.getCity());
        // e.setRegion(dto.getRegion());
        // e.setPostalCode(dto.getPostalCode());
        // e.setCountry(dto.getCountry());
        // e.setHomePhone(dto.getHomePhone());
        // e.setExtension(dto.getExtension());
        // e.setNotes(dto.getNotes());
        // e.setReportsTo(dto.getReportsTo());
        // e.setPhotoPath(dto.getPhotoPath());
        // ... add any other fields that exist in both classes

        return e;
    }
}