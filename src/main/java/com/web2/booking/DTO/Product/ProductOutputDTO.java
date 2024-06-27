package com.web2.booking.DTO.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.web2.booking.enums.Category;

public record ProductOutputDTO(UUID id, String title, String description, String mainImage,
    String city, String state, double price, double customerScore, int numberOfReviews,
    int discount, LocalDateTime createdAt, LocalDate startDate,
    LocalDate endDate, Category category, int quantity) {

}
