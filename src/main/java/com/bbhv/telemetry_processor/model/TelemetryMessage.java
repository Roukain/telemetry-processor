package com.bbhv.telemetry_processor.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

// AJOUTEZ CETTE CLASSE EN REMPLACANT VOTRE CODE ACTUEL
@Data
@NoArgsConstructor
@AllArgsConstructor // Nécessaire pour certains outils de désérialisation
public class TelemetryMessage implements Serializable {

    private String carId;
    private double latitude;
    private double longitude;
    private int h2Level;
    private long timestamp;
}