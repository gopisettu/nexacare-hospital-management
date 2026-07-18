package com.nexacare.hospital.service;

import com.nexacare.hospital.dto.request.BookAppointmentDto;
import com.nexacare.hospital.enums.AppointmentStatus;
import com.nexacare.hospital.exception.ResourceNotFoundException;
import com.nexacare.hospital.mapper.dtotoentity.AppointmentMapper;
import com.nexacare.hospital.model.Appointment;
import com.nexacare.hospital.model.Doctor;
import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import com.nexacare.hospital.repositories.AppointmentRepository;
import com.nexacare.hospital.repositories.DoctorRepository;
import com.nexacare.hospital.repositories.PatientRepository;
import com.nexacare.hospital.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppointmentService {
    private  final UserRepository userRepository;
    private  final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
private final AppointmentMapper appointmentMapper;
private final AppointmentRepository appointmentRepository;
    public void bookDoctor(String username, BookAppointmentDto bookAppointmentDto) {
//step1: check userName valid for both doctor and patient ,handle orElseThrow
        User user =userRepository.findByUserUsername(username).
                orElseThrow(()->new ResourceNotFoundException("Patient username not found"));
        Patient patient = patientRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        Doctor doctor=doctorRepository.findById(bookAppointmentDto.doctorId())
                .orElseThrow(()->new ResourceNotFoundException("Doctor Id not found"));

//step2. Convert Dto to entity using mapper
        Appointment appointment=appointmentMapper.mapDoctorDtoToEntity(bookAppointmentDto);
//step3: set patient and doctor
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

//step4. Set the Appointment status as PENDING
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);
//        step 5:save the appointment
        appointmentRepository.save(appointment);


    }
}
