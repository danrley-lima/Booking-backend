package com.web2.booking.DTO.Product;

import java.time.LocalDate;

public record CreateProductInputDTO(String title, String description, String city, String state,
    String mainImage, double price, double customerScore, int numberOfReviews, int discount, int quantity,
    double totalPrice, LocalDate startDate, LocalDate endDate, String coupon) {

}
