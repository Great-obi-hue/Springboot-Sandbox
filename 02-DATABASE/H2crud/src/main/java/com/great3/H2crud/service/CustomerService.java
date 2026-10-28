package com.great3.H2crud.service;

import java.util.List;
import com.great3.H2crud.entity.Customer;
import com.great3.H2crud.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Integer id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer){
        Customer existingCustomer = getCustomerById(id);
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setGender(updatedCustomer.getGender());

        return customerRepository.save(existingCustomer);
    }

    public void deleteCustomer(Integer id){
        Customer existingCustomer = getCustomerById(id);
        customerRepository.delete(existingCustomer);
    }
}