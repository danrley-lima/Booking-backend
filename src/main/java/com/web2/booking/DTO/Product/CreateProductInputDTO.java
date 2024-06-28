package com.web2.booking.DTO.Product;

import java.time.LocalDate;

import com.web2.booking.enums.Category;

public record CreateProductInputDTO(String title, String description, String city, String state,
		String mainImage, double price, double customerScore, int numberOfReviews, int discount, LocalDate startDate,
		LocalDate endDate, String coupon, Category category, int quantity) {

}
