package com.example.JasperFirstProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    public ResponseEntity<?> generateReport(@RequestParam(value = "format", required = false) String format) {
        try {


            // Mock data (replace with actual data)
            List<Employee> employees = List.of(
                    new Employee("John Doe", "Manager", 5000.0),
                    new Employee("Jane Smith", "Developer", 4000.0)
            );

            // Report parameters (optional)
            HashMap<String, Object> params = new HashMap<>();
            params.put("Title", "Employee Report");

            // Generate report
            byte[] report = reportService.generateReport(employees, params);

            // Set HTTP headers for PDF response
          /*  HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=report.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);*/
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employee_report.pdf");
            headers.setContentType(MediaType.APPLICATION_PDF);
            return new ResponseEntity<>(report, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}




































