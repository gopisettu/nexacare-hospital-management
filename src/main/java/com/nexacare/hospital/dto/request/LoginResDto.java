package com.nexacare.hospital.dto.request;

import com.nexacare.hospital.enums.Role;

public record LoginResDto(
        Long userId,
        String username,
        Role role
) {

}
