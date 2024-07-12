package com.web2.booking.DTO.Login;

import com.web2.booking.enums.Role;

import java.util.UUID;

public record LoginResponseDTO(String token, UUID id, Role role) {
}
