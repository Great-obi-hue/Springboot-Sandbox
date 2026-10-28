package com.great.fraudservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.great.fraudservice.dto.FraudResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/fraud-check") // Updated to match Feign client
@Slf4j
public class FraudController {

    @GetMapping("/{customerId}")
    public FraudResponse checkFraud(@PathVariable("customerId") Integer customerId) { // Updated to Integer

        log.info("Fraud check started for customerId={}", customerId);

        boolean isFraudulent = customerId.toString().startsWith("9");

    FraudResponse response = new FraudResponse(customerId.toString(), isFraudulent);

        log.info("Fraud check completed for customerId={}, result={}",
                customerId, isFraudulent);

        return response;
        
    }
}