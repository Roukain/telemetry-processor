package com.bbhv.telemetry_processor.service;

import com.bbhv.telemetry_processor.model.TelemetryMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimulationRunner implements CommandLineRunner {
    private final KafkaProducerService producerService;

    public SimulationRunner(KafkaProducerService producerService) {
        this.producerService = producerService;
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting car simulation...");

        // Simuler l'envoi de données pour la voiture 1
        TelemetryMessage car1 = new TelemetryMessage();
        car1.setCarId("BBHV-001");
        car1.setLatitude(48.85);
        car1.setLongitude(2.35);
        car1.setH2Level(80);
        car1.setTimestamp(System.currentTimeMillis());
        producerService.sendMessage(car1);

        // Simuler l'envoi de données pour la voiture 2
        TelemetryMessage car2 = new TelemetryMessage();
        car2.setCarId("BBHV-002");
        car2.setLatitude(45.76);
        car2.setLongitude(4.83);
        car2.setH2Level(95);
        car2.setTimestamp(System.currentTimeMillis());
        producerService.sendMessage(car2);
    }
}
