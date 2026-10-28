package com.great.customerservice.service;

import com.great.amqp.producer.RabbitMQMessageProducer;
import com.great.clients.fraud.FraudCheckResponse;
import com.great.clients.fraud.FraudClient;
import com.great.clients.notification.NotificationRequest;
import com.great.customerservice.dto.CustomerRegistrationRequest;
import com.great.customerservice.entity.Customer;
import com.great.customerservice.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer rabbitMQMessageProducer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.getFirstName()) // Updated to standard getter
                .lastName(request.getLastName())   // Updated to standard getter
                .email(request.getEmail())         // Updated to standard getter
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse =
                fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome...",
                        customer.getFirstName())
        );
        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );

    }
}