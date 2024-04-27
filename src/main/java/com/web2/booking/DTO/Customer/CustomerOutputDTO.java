package com.web2.booking.DTO.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerOutputDTO(UUID id, String firstName, String lastName, String email, String phoneNumber,
    String CPF, LocalDate dateOfBirth, String nationality, String gender, LocalDateTime createdAt) {

}
