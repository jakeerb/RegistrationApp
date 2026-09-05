package com.jakeer.RegistrationApp.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class RegistrationKafkaConsumer {

    @KafkaListener(
            topics = "registration-topic",
            groupId = "registration-group"
    )
    public void consumeRegistration(String message) {

        System.out.println("Received registration: " + message);
    }
}