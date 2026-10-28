package com.great.customerservice.controller;

import com.great.customerservice.dto.CustomerRegistrationRequest;
import com.great.customerservice.dto.FraudResponse;
import com.great.customerservice.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/customer")
@Slf4j
public class CustomerController {

    private final CustomerService customerService;
    private final RestTemplate restTemplate;

    public CustomerController(CustomerService customerService, RestTemplate restTemplate) {
        this.customerService = customerService;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public void registerCustomer(@RequestBody CustomerRegistrationRequest request) {
        log.info("New customer registration {}", request);
        customerService.registerCustomer(request);
    }

    @GetMapping("/{customerId}")
    public String checkCustomer(@PathVariable("customerId") String customerId) {
        log.info("Customer request received to be sent to fraud service: {}", customerId);

        String url = "http://localhost:8081/api/fraud/" + customerId;

        FraudResponse response = restTemplate.getForObject(url, FraudResponse.class);

        log.info("Fraud service response received: {}", response);

return response.getIsFraudster()                ? "Customer is FRAUDULENT"
                : "Customer is CLEAN";
    }
}