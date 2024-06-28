package com.web2.booking.DTO.Login;

import java.util.UUID;

public record LoginResponseDTO(String token, UUID id) {
}
