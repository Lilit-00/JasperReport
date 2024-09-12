package com.example.JasperFirstProject;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
@Service
public class ReportService {

    public byte[] generateReport(List<?> data, Map<String, Object> params) throws JRException {
        // Load the JRXML file (ensure correct file path)
        InputStream reportStream = getClass().getResourceAsStream("/reports/sample_report.jrxml");

        // Compile the JRXML file
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Create data source for the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

        // Fill the report with data
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);

        // Export the report to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
