package com.nexacare.hospital.controller;

import com.nexacare.hospital.dto.request.DoctorProfileDto;
import com.nexacare.hospital.dto.request.DoctorRegisterDto;
import com.nexacare.hospital.dto.response.AppointmentResDto;
import com.nexacare.hospital.dto.response.DoctorResDto;
import com.nexacare.hospital.model.Doctor;
import com.nexacare.hospital.service.DoctorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/get-allDoctor")
    public List<DoctorResDto> getAllDoctor(@RequestParam(required = false,defaultValue = "0") Integer page,
                                           @RequestParam(required = true,defaultValue = "4") Integer size){
        return doctorService.getAllDoctor(page,size);
    }

    @GetMapping("/get-DoctorByUsername/{username}")
    public DoctorResDto getDoctorByUsername(@PathVariable String username){
        return doctorService.getDoctorByUsername(username);
    }

    @PutMapping("/deActivate-ByDoctor/{username}")
    public void deActiveDoctor(@PathVariable String username)
    {
        doctorService.deActivateDoctor(username);
    }

    @GetMapping("/allAppointment-ByDoctor/{username}")
    public List<AppointmentResDto> showAllAppointmentByDoctor(@PathVariable String username,
                                                        @RequestParam(required = false,defaultValue = "0") Integer page,
                                                        @RequestParam(required = false,defaultValue = "3") Integer size){
        return doctorService.showAllAppointmentByDoctor(username,page,size);

    }
}

