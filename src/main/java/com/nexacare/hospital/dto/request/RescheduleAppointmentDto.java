package com.nexacare.hospital.dto.request;

import com.nexacare.hospital.enums.AppointmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record RescheduleAppointmentDto(
        Long appointmentId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        AppointmentStatus appointmentStatus
) {
}

