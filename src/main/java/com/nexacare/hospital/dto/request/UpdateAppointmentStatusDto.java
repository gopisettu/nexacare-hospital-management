package com.nexacare.hospital.dto.request;

import com.nexacare.hospital.enums.AppointmentStatus;

public record UpdateAppointmentStatusDto(
        Long appointmentId,
        AppointmentStatus appointmentStatus
) {
}
