package com.web2.booking.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.web2.booking.enums.Category;

import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductModel {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  // @NotBlank
  private String title;

  @Nullable
  private String description;

  @Nullable
  private String mainImage;

  @Nullable
  private String city;

  @Nullable
  private String state;

  @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  @Nullable
  private List<PhotoModel> photos;

  @NotNull
  @Min(0)
  private double price;

  @Nullable
  private double customerScore;

  @Nullable
  private int numberOfReviews;

  @Nullable
  @Min(0)
  @Column(columnDefinition = "INT default 0")
  private int discount;

  @Nullable
  @Min(0)
  private int quantity;

  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate startDate;

  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate endDate;

  @Nullable
  private String coupon;

  @Nullable
  @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<RatingModel> ratings;

  @Enumerated(EnumType.STRING)
  private Category category;
}
