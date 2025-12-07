package com.bbhv.telemetry_processor.service;

import com.bbhv.telemetry_processor.model.TelemetryMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Service
public class KafkaProducerService {
    @Value("${telemetry.topic.raw}")
    private String topicName;

    private final KafkaTemplate<String, TelemetryMessage> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, TelemetryMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(TelemetryMessage message) {
        // Appel correct avec topic, clé (carId), et message
        kafkaTemplate.send(topicName, message.getCarId(), message);
        System.out.println("Message sent to Kafka: " + message.getCarId());
    }
}