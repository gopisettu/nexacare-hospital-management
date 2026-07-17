package com.nexacare.hospital.controller;

import com.nexacare.hospital.dto.request.PatientProfileDto;
import com.nexacare.hospital.dto.request.PatientRegisterDto;
import com.nexacare.hospital.service.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;
    @PostMapping("/register-patient")
    public void registerPatient(@RequestBody PatientRegisterDto patientDto){
        patientService.registerPatient(patientDto);
    }
    @PutMapping("/update-patientProfile/{username}")
    public void updateProfile(@RequestBody PatientProfileDto patientProfileDto, @PathVariable String username){
        patientService.updateProfile(patientProfileDto,username);
    }
//    @PostMapping("/book-appointment")
//    public void bookAppointment(@RequestBody )

}
