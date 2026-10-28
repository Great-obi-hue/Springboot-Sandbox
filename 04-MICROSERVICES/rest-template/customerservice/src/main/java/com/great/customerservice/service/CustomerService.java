package com.great.customerservice.service;

import com.great.customerservice.entity.Customer;
import com.great.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private RestTemplate restTemplate;

    // URL of the Order Microservice
    private final String ORDER_URL = "http://localhost:8081/orders";




    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }




    public Customer getCustomerById(Long id) {

        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }




    public Customer createCustomer(Customer customer) {

        return customerRepository.save(customer);
    }




    public Customer updateCustomer(Long id, Customer updatedCustomer) {

        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());

        return customerRepository.save(existingCustomer);
    }



    public void deleteCustomer(Long id) {

        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found");
        }

        customerRepository.deleteById(id);
    }




    public Object getCustomerOrders(Long customerId) {

        String url = ORDER_URL + "/customer/" + customerId;

        return restTemplate.getForObject(url, Object.class);
    }
}