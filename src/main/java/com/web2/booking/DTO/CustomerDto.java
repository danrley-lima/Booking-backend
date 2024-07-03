package com.web2.booking.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class CustomerDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    // Adicionar outros campos caso necessários
}