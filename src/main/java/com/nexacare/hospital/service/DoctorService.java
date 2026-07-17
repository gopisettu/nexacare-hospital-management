package com.nexacare.hospital.service;

import com.nexacare.hospital.dto.request.DoctorProfileDto;
import com.nexacare.hospital.dto.request.DoctorRegisterDto;
import com.nexacare.hospital.enums.Role;
import com.nexacare.hospital.exception.ResourceNotFoundException;
import com.nexacare.hospital.mapper.dtotoentity.DoctorMapper;
import com.nexacare.hospital.model.Doctor;
import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import com.nexacare.hospital.repositories.DoctorRepository;
import com.nexacare.hospital.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    public void registerDoctor(@Valid DoctorRegisterDto doctorRegisterDto) {

        Doctor doctor = new Doctor();
        User user = new User();

        user.setUsername(doctorRegisterDto.username());
        user.setPassword(doctorRegisterDto.password());
        user.setRole(Role.DOCTOR);

        user = userRepository.save(user);

        System.out.println("User Saved : " + user.getId());

        doctor.setUser(user);

        doctor = doctorRepository.save(doctor);

        System.out.println("Doctor Saved : " + doctor.getId());
    }

    public void updateProfile(@Valid DoctorProfileDto doctorProfileDto,String username) {
        User user= userRepository.findByUser(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found"));
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Doctor not found"));
doctor= DoctorMapper.mapDtotoDoctorEntity(doctorProfileDto,doctor);
doctorRepository.save(doctor);

    }
}
