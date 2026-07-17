package com.nexacare.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PatientRegisterDto(
        @NotBlank(message = "Username is mandatory")
        String username,
        @NotBlank(message = "Password is mandatory")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password
) {
}