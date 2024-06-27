package com.web2.booking.DTO.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


public record CreateProductOutputDTO(UUID id, String name, String description, String mainImage,
        String city, String state, double price, double customerScore, int numberOfReviews,
        int discount, int quantity, LocalDateTime createdAt, LocalDate startDate,
        LocalDate endDate) {

}
