package com.web2.booking.DTO.Product;

import java.time.LocalDate;

public record CreateProductInputDTO(String name, String description, String city, String state,
    String mainImage, double price, double customerScore, int numberOfReviews, int discount, int quantity,
     LocalDate startDate, LocalDate endDate, String coupon) {

}
