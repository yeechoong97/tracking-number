package com.example.assessment.service;

import com.example.assessment.model.TrackingDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.stream.IntStream;

@Service
public class TrackingService {

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();
    private static final Integer FIXED_LENGTH = 16;


    public TrackingDTO generateTrackingNumber(String originCountryId,
                                              String destinationCountryId) {
        if (ObjectUtils.isEmpty(originCountryId)) {
            originCountryId = this.generateRandomSuffix(2);
        }

        if (ObjectUtils.isEmpty(destinationCountryId)) {
            destinationCountryId = this.generateRandomSuffix(2);
        }


        UUID randomId = UUID.randomUUID();
        String hashValue = String.valueOf(Math.abs(randomId.toString().hashCode()));
        String suffix = this.generateRandomSuffix(FIXED_LENGTH - hashValue.length() - 4);
        StringBuilder stringBuilder = new StringBuilder().append(originCountryId).append(hashValue).append(suffix).append(destinationCountryId);

        TrackingDTO trackingDTO = new TrackingDTO();
        trackingDTO.setCreatedAt(OffsetDateTime.now());
        trackingDTO.setTrackingNumber(stringBuilder.toString());
        return trackingDTO;
    }


    private String generateRandomSuffix(int length) {
        StringBuilder sb = new StringBuilder(length);

        IntStream.range(0, length).forEach(item -> {
            char randomChar = ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length()));
            sb.append(randomChar);
        });

        return sb.toString();
    }


}
