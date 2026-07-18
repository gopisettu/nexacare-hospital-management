package com.nexacare.hospital.dto.response;

import com.nexacare.hospital.enums.Role;

public record LoginResDto(
        Long userId,
        String username,
        Role role
) {

}
