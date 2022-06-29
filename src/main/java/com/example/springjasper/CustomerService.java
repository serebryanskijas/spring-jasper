package com.example.springjasper;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public List<Customer> findAllSorted(String sort){
        return customerRepository.findAll(Sort.by(Sort.Direction.ASC,sort));
    }
}
