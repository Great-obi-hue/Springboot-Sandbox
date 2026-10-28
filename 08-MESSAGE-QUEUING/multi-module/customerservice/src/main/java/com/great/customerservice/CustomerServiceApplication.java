package com.great.customerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(
    scanBasePackages = {
        "com.great.customerservice",
        "com.great.amqp" // <-- Add this line so Spring finds the RabbitMQ producer bean
    }
)
@EnableFeignClients(
    basePackages = "com.great.clients"
)
public class CustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
}