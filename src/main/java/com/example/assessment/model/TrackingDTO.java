package com.example.assessment.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.time.OffsetDateTime;

@Data
public class TrackingDTO {

    @JsonProperty("tracking_number")
    private String trackingNumber;

    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

}
