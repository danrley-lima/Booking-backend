package com.web2.booking.DTO;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class BookingDto {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    // Adicionar outros campos caso necessários

}