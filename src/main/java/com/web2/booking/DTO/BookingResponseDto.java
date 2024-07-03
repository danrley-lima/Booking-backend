package com.web2.booking.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class BookingResponseDto {
    private UUID id;
    private CustomerDto customer;
    private ProductDto product;
    private LocalDateTime bookingDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalPrice;
    // Adicionar outros campos caso necessários
}