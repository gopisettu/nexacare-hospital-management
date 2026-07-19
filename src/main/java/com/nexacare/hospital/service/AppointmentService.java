package com.nexacare.hospital.service;

import com.nexacare.hospital.dto.request.BookAppointmentDto;
import com.nexacare.hospital.dto.response.AppointmentResDto;
import com.nexacare.hospital.enums.AppointmentStatus;
import com.nexacare.hospital.exception.ResourceNotFoundException;
import com.nexacare.hospital.mapper.dtotoentity.AppointmentMapper;
import com.nexacare.hospital.mapper.entitytodto.AppointmentEntityToDto;
import com.nexacare.hospital.model.Appointment;
import com.nexacare.hospital.model.Doctor;
import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import com.nexacare.hospital.repositories.AppointmentRepository;
import com.nexacare.hospital.repositories.DoctorRepository;
import com.nexacare.hospital.repositories.PatientRepository;
import com.nexacare.hospital.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AppointmentService {
    private  final UserRepository userRepository;
    private  final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
private final AppointmentMapper appointmentMapper;
private final AppointmentRepository appointmentRepository;
private  final AppointmentEntityToDto appointmentEntityToDto;
    public void bookDoctor(String username, BookAppointmentDto dto) {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Patient username not found"));

        Patient patient = patientRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found"));

        Appointment appointment = appointmentMapper.mapDoctorDtoToEntity(dto);

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus(AppointmentStatus.PENDING);

        appointmentRepository.save(appointment);
    }

    // Doctor views appointments
    public List<AppointmentResDto> showAllAppointmentByDoctor(String username,
                                                              Integer page,
                                                              Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Appointment> appointments =
                appointmentRepository.findByDoctorUserUsername(username, pageable);
        return appointments.stream()
                .map((a) -> appointmentEntityToDto.mapAppointmentEntityToDto(a))
                .toList();
    }
}
