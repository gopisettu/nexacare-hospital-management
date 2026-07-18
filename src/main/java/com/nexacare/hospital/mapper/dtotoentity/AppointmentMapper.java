package com.nexacare.hospital.mapper.dtotoentity;

import com.nexacare.hospital.dto.request.BookAppointmentDto;
import com.nexacare.hospital.enums.AppointmentStatus;
import com.nexacare.hospital.model.Appointment;

import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {
    public Appointment mapDoctorDtoToEntity(BookAppointmentDto bookAppointmentDto){
        Appointment appointment=new Appointment();
        appointment.setAppointmentDate(bookAppointmentDto.appointmentDate());
        appointment.setAppointmentTime(bookAppointmentDto.appointmentTime());
        appointment.setReason(bookAppointmentDto.reason());
        appointment.setNotes(bookAppointmentDto.notes());
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);
        return appointment;
    }
}
