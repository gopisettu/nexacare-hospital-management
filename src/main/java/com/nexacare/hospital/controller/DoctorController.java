package com.nexacare.hospital.controller;

import com.nexacare.hospital.dto.request.DoctorProfileDto;
import com.nexacare.hospital.dto.request.DoctorRegisterDto;
import com.nexacare.hospital.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@AllArgsConstructor
public class DoctorController {
    private final DoctorService doctorService;

    @PostMapping("/register-doctor")
    public void registerDoctor( @Valid @RequestBody DoctorRegisterDto doctorRegisterDto){
        doctorService.registerDoctor(doctorRegisterDto);
    }
    @PutMapping("/update-doctorProfile/{username}")
    public void updateProfile(@Valid @RequestBody DoctorProfileDto doctorProfileDto,@PathVariable String username){
        doctorService.updateProfile(doctorProfileDto,username);
    }

}
