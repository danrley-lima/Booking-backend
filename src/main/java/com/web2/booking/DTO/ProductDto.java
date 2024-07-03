package com.web2.booking.DTO;

import java.util.UUID;

import lombok.Data;

@Data
public class ProductDto {
    private UUID id;
    private String title;
    private String description;
    private double price;
    // Adicionar outros campos caso necessários
}