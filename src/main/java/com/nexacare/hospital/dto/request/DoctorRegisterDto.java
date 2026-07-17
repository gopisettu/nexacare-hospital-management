package com.nexacare.hospital.dto.request;

import jakarta.validation.constraints.NotBlank;

public record DoctorRegisterDto(
        @NotBlank( message="UserName cannot be null")
        String username,
        @NotBlank(message="Password cannot be null")
        String password
) {
}
