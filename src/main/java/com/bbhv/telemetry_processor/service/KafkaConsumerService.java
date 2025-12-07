package com.bbhv.telemetry_processor.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    private final StringRedisTemplate redisTemplate;

    public KafkaConsumerService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @KafkaListener(topics = "mon-topic", groupId = "my-group")
    public void consume(String message) {
        System.out.println("Message reçu : " + message);
        // Écriture dans Redis
        redisTemplate.opsForValue().set("kafka:" + System.currentTimeMillis(), message);
    }
}
