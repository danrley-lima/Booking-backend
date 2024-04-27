package com.web2.booking.DTO.Customer;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateCustomerOutputDTO(UUID id, String firstName, String lastName, String email,
    LocalDateTime createdAt) {

}
