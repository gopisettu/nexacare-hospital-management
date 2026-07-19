package com.nexacare.hospital.service;

import com.nexacare.hospital.dto.request.*;
import com.nexacare.hospital.dto.response.AppointmentResDto;
import com.nexacare.hospital.enums.AppointmentStatus;
import com.nexacare.hospital.exception.ResourceNotFoundException;
import com.nexacare.hospital.mapper.PrescriptionMapper;
import com.nexacare.hospital.mapper.dtotoentity.AppointmentMapper;
import com.nexacare.hospital.mapper.entitytodto.AppointmentEntityToDto;
import com.nexacare.hospital.model.*;
import com.nexacare.hospital.repositories.*;
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
private final PrescriptionMapper prescriptionMapper;
private final MedicineRepository medicineRepository;
private  final PrescriptionItemRepository prescriptionItemRepository;
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

    public List<AppointmentResDto> showAllAppointmentByPatient(String username, Integer page, Integer size) {
        Pageable pageable=PageRequest.of(page,size);
       List<Appointment> appointments=appointmentRepository.findByPatientUserUsername(username,pageable);
        return appointments.stream()
                .map((a) -> appointmentEntityToDto.mapAppointmentEntityToDto(a))
                .toList();

    }

    public void updateAppointmentStatus(String username, UpdateAppointmentStatusDto updateAppointmentStatusDto) {
//step1 : check valid doctor
        Doctor doctor = doctorRepository.findByUserUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found"));
//       step 2:get for appointment for patch update
        Appointment appointment=appointmentRepository.findById(updateAppointmentStatusDto.appointmentId())
                .orElseThrow(()->new ResourceNotFoundException("Appointment Id not Found"));
// step 3. check for the particular appointment belong to the doctor
        if( ! appointment.getDoctor().getId().equals(doctor.getId())){
            throw  new RuntimeException("You are not authorized to update this appointment");
        }
//        step 4: update the appointment status as per the dto
        appointment.setAppointmentStatus(updateAppointmentStatusDto.appointmentStatus());
//        step 5: save the appointment
        appointmentRepository.save(appointment);

    }

    public void rescheduleAppointment(String username, RescheduleAppointmentDto rescheduleAppointmentDto) {
//step1 : check valid doctor
        Doctor doctor = doctorRepository.findByUserUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found"));
//       step 2:get for appointment for patch update
        Appointment appointment=appointmentRepository.findById(rescheduleAppointmentDto.appointmentId())
                .orElseThrow(()->new ResourceNotFoundException("Appointment Id not Found"));
// step 3. check for the particular appointment belong to the doctor
        if( ! appointment.getDoctor().getId().equals(doctor.getId())){
            throw  new RuntimeException("You are not authorized to update this appointment");
        }
//        step4: update the appointment and save it
        appointment.setAppointmentTime(rescheduleAppointmentDto.appointmentTime());
        appointment.setAppointmentDate(rescheduleAppointmentDto.appointmentDate());
        appointment.setAppointmentStatus(rescheduleAppointmentDto.appointmentStatus());
        appointmentRepository.save(appointment);
    }

    public void submitPrescription(String username,
                                   SubmitPrescriptionDto submitPrescriptionDto) {

        // Step 1: Validate doctor
        Doctor doctor = doctorRepository.findByUserUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor username not found"));

        // Step 2: Validate appointment
        Appointment appointment = appointmentRepository
                .findById(submitPrescriptionDto.appointmentId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Appointment not found"));

        // Step 3: Check appointment belongs to this doctor
        if (!appointment.getDoctor().getId().equals(doctor.getId())) {
            throw new RuntimeException("You are not authorized.");
        }

        // Step 4: Save each prescribed medicine
        for (PrescriptionItemDto dto : submitPrescriptionDto.medicines()) {

            Medicine medicine = medicineRepository.findById(dto.medicineId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException("Medicine not found"));

            PrescriptionItem item = prescriptionMapper.mapDtoToEntity(dto);

            item.setAppointment(appointment);
            item.setMedicine(medicine);

            prescriptionItemRepository.save(item);
        }
    }
}
