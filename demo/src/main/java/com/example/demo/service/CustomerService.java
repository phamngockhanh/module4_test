package com.example.demo.service;

import com.example.demo.entity.Customer;
import com.example.demo.repository.ICustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService{
    private final ICustomerRepo customerRepo;

    public CustomerService(ICustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }
}
