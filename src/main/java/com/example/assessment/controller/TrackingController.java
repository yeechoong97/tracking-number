package com.example.assessment.controller;

import com.example.assessment.model.TrackingDTO;
import com.example.assessment.service.TrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Pattern;
import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/v1")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/next-tracking-number")
    public ResponseEntity<TrackingDTO> retrieveTrackingNumber(

            @RequestParam(value = "origin_country_id", required = false)
            @Pattern(regexp = "^[A-Z]{2}$", message = "origin_country_id must be 2 characters (A-Z)")
            String originCountryId,
            @RequestParam(value = "destination_country_id", required = false)
            @Pattern(regexp = "^[A-Z]{2}$", message = "origin_country_id must be 2 characters (A-Z)")
            String destinationCountryId,
            @RequestParam(value = "weight", required = false)
            @Pattern(regexp = "^\\d+(\\.\\d{1,3})?$", message = "Weight must be a number with up to 3 decimal places")
            String weight,
            @RequestParam(value = "created_at", required = false) OffsetDateTime createdAt,
            @RequestParam(value = "customer_id", required = false) UUID customerId,
            @RequestParam(value = "customer_name", required = false) String customerName,
            @RequestParam(value = "customer_slug", required = false) String customerSlug
            ) {
        TrackingDTO trackingDTO = this.trackingService.generateTrackingNumber(originCountryId, destinationCountryId);
        return ResponseEntity.ok(trackingDTO);
    }


}
