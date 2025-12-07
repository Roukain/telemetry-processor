package com.bbhv.telemetry_processor.service;

import com.bbhv.telemetry_processor.model.TelemetryMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.data.redis.core.RedisTemplate; // <-- Utilisez l'objet complet
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private static final String VEHICLE_STATUS_KEY_PREFIX = "status:";
    private final RedisTemplate<String, Object> redisTemplate; // <-- Objet complet

    public KafkaConsumerService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @KafkaListener(topics = "car-telemetry-raw", groupId = "telemetry-group")
    public void consume(TelemetryMessage message) { // <-- LE MESSAGE EST UN OBJET, PAS UNE CHAÎNE

        // --- LOGIQUE D'ANONYMISATION ---
        // La clé doit être anonyme, basée sur l'ID de la voiture
        String carId = message.getCarId();
        String anonymizedKey = VEHICLE_STATUS_KEY_PREFIX + carId;

        System.out.println("Message reçu pour CarId: " + carId);

        // Écriture dans Redis (Stockage Chaud)
        redisTemplate.opsForValue().set(anonymizedKey, message);

        System.out.println("CONSUMED, ANONYMIZED & STORED in Redis: " + anonymizedKey);
        System.out.println("  -> Lat/Lon: " + message.getLatitude());
    }
}