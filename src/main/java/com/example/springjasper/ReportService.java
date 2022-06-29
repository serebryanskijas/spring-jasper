package com.example.springjasper;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    public String exportReport(List<Customer> customers, String template, String report, String reportFormat) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile("classpath:templates/" + template);
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(customers);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        if (reportFormat.equals("html"))
            JasperExportManager.exportReportToHtmlFile(jasperPrint,
                    "D:\\Project_Upload\\SpringJasper\\src\\main\\resources\\public\\"+report);
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String sdate = dateFormat.format(calendar.getTime());
        return "Report has been generated"+sdate;
    }
}
