package com.nexacare.hospital.dto.request;

import com.nexacare.hospital.enums.Reason;
import com.nexacare.hospital.model.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;

public record BookAppointmentDto(

        Long doctorId,
        LocalDate appointmentDate,
        LocalTime appointmentTime,
        Reason reason,
        String notes
        //appointment status update in service layer


) {
}
