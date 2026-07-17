package com.nexacare.hospital.service;

import com.nexacare.hospital.dto.request.PatientProfileDto;
import com.nexacare.hospital.dto.request.PatientRegisterDto;
import com.nexacare.hospital.enums.Role;
import com.nexacare.hospital.exception.ResourceNotFoundException;
import com.nexacare.hospital.mapper.dtotoentity.PatientMapper;
import com.nexacare.hospital.model.Patient;
import com.nexacare.hospital.model.User;
import com.nexacare.hospital.repositories.PatientRepository;
import com.nexacare.hospital.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
@AllArgsConstructor

public class PatientService {
    private  final PatientRepository patientRepository;
    private final UserRepository userRepository;
    public void registerPatient(PatientRegisterDto patientDto) {
        Patient patient=new Patient();
        User user=new User();
        //setting username,password and role
        user.setUsername(patientDto.username());
        user.setPassword(patientDto.password());
        user.setRole(Role.PATIENT);
        user =userRepository.save(user);
        //save the user
        patient.setUser(user);
        //attach the user to the doctor
        patientRepository.save(patient);

    }

    public void updateProfile(PatientProfileDto patientProfileDto, String username) {
        User user=userRepository.findByUser(username)
                .orElseThrow(()->new ResourceNotFoundException("Patient UserName not found"));
         Patient patient=patientRepository.findByUserId(user.getId()).orElseThrow(()-> new ResourceNotFoundException("Patient Id not found"));
               patient=PatientMapper.mapDtoToPatient(patientProfileDto,patient);
         patientRepository.save(patient);
    }
}
