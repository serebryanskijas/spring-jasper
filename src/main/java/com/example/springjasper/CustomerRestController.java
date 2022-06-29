package com.example.springjasper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {
    @Autowired
    CustomerService customerService;

    @GetMapping(value = "customers", produces = "application/json")
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }
}
