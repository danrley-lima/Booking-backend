package com.web2.booking.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  private String title;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  @NotBlank
  private String image;

  @NotNull
  @Positive
  private double price;

  @Nullable
  private double customerScore;

  @Nullable
  private int numberOfReviews;

  @Min(0)
  @Column(columnDefinition = "INT default 0")
  private int discount;

  @Positive
  private double totalPrice;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
