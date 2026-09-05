package com.jakeer.RegistrationApp.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RegistrationKafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public RegistrationKafkaProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRegistration(String message) {
        kafkaTemplate.send("registration-topic", message);
    }
}