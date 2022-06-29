package com.example.springjasper;

import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    CustomerService customerService;

    @Autowired
    ReportService reportService;

    @GetMapping(value = "/report1", params = {})
    public String generateReport(){
        List<Customer> customers = customerService.findAll();
       try {
            reportService.exportReport(customers, "report.jrxml", "report1.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report1.html";
    }
    @GetMapping(value = "/report1", params = {"sort"})
    public String generateReportSort(@RequestParam("sort") String sort){
        List<Customer> customers = customerService.findAllSorted(sort);
        try {
            reportService.exportReport(customers, "report.jrxml", "report2.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report2.html";
    }

    @GetMapping(value = "/report2", params = {})
    public String generateReportSort(){
        List<Customer> customers = customerService.findAllSorted("company");
        try {
            reportService.exportReport(customers, "report.jrxml", "report3.html", "html");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JRException e) {
            e.printStackTrace();
        }
        return "redirect:/report3.html";
    }
}
