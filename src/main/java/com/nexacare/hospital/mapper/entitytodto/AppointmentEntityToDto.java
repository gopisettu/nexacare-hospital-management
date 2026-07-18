package com.nexacare.hospital.mapper.entitytodto;

import com.nexacare.hospital.dto.response.AppointmentResDto;
import com.nexacare.hospital.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentEntityToDto {

    public AppointmentResDto mapAppointmentEntityToDto(Appointment appointment){
        AppointmentResDto appointmentResDto=new AppointmentResDto(
                appointment.getPatient().getId(),
                appointment.getAppointmentDate(),
                appointment.getAppointmentTime(),
                appointment.getReason(),
                appointment.getNotes(),
                appointment.getAppointmentStatus()
        );
        return appointmentResDto;
    }
}
