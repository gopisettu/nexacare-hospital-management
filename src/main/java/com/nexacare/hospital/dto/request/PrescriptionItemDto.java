package com.nexacare.hospital.dto.request;

import com.nexacare.hospital.enums.Frequency;
import com.nexacare.hospital.enums.Route;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PrescriptionItemDto(

        @NotNull
        Long medicineId,

        @NotNull
        @Min(1)
        Integer quantity,

        String dosage,

        Frequency frequency,

        Integer durationDays,

        Route route,

        String instruction

) {}