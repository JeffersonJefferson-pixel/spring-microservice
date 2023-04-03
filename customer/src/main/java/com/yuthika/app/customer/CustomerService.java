package com.yuthika.app.customer;

import com.yuthika.app.amqp.RabbitMQMessageProducer;
import com.yuthika.app.clients.fraud.FraudCheckResponse;
import com.yuthika.app.clients.fraud.FraudClient;
import com.yuthika.app.clients.notification.NotificationClient;
import com.yuthika.app.clients.notification.NotificationRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final FraudClient fraudClient;
    private final RabbitMQMessageProducer producer;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        // todo: check if email valid
        // todo: check if email not taken
        customerRepository.saveAndFlush(customer); // save first to get id
        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                "registration is successful",
                "customer-service",
                request.email(),
                customer.getId()
        );

//        notificationClient.sendNotification(notificationRequest);

        producer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
