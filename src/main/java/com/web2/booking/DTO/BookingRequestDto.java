package com.web2.booking.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class BookingRequestDto {
    private UUID customerId;
    private UUID productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double totalPrice;
    // // Adicionar outros campos caso necessários
}
