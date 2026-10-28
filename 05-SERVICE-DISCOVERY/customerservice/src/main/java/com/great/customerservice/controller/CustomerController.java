package com.great.customerservice.controller;

import com.great.customerservice.entity.Customer;
import com.great.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;




    @GetMapping
    public List<Customer> getAllCustomers() {

        return customerService.getAllCustomers();
    }




    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {

        return customerService.getCustomerById(id);
    }




    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {

        return customerService.createCustomer(customer);
    }




    @PutMapping("/{id}")
    public Customer updateCustomer(
            @PathVariable Long id,
            @RequestBody Customer customer) {

        return customerService.updateCustomer(id, customer);
    }




    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.deleteCustomer(id);

        return "Customer deleted successfully";
    }




    @GetMapping("/{id}/orders")
    public Object getCustomerOrders(@PathVariable Long id) {

        return customerService.getCustomerOrders(id);
    }
}