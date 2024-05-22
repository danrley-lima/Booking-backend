package com.web2.booking.DTO.Product;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductOutputDTO(UUID id, String title, String city, String state, double price,
        double customerScore, int numberOfReviews, int discount, double totalPrice, LocalDateTime createdAt) {

}
