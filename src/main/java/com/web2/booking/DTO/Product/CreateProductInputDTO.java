package com.web2.booking.DTO.Product;

public record CreateProductInputDTO(String title, String description, String city, String state, String mainImage,
    double price, double customerScore, int numberOfReviews, int discount, double totalPrice) {

}
