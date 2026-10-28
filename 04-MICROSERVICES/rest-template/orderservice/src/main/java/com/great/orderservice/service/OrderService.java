package com.great.orderservice.service;

import com.great.orderservice.dto.Customer;
import com.great.orderservice.entity.Order;
import com.great.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final String CUSTOMER_URL = "http://localhost:8080/customers";




    public Customer createCustomer(Customer customer) {

        return restTemplate.postForObject(
                CUSTOMER_URL,
                customer,
                Customer.class
        );
    }




    public void updateCustomer(Long id, Customer customer) {

        restTemplate.put(
                CUSTOMER_URL + "/" + id,
                customer
        );
    }




    public void deleteCustomer(Long id) {

        restTemplate.delete(
                CUSTOMER_URL + "/" + id
        );
    }




    public Map<String, Object> createOrder(Order order) {



        Order savedOrder = orderRepository.save(order);

        Map<String, Object> response = new HashMap<>();

        response.put("order", savedOrder);


        return response;
    }




    public List<Order> getOrdersByCustomerId(Long customerId) {

        return orderRepository.findByCustomerId(customerId);
    }
}