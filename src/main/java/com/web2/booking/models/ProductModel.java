package com.web2.booking.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
  private String description;

  @NotBlank
  private String mainImage;

  @NotBlank
  private String city;

  @NotBlank
  private String state;

  // @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  // private List<PhotoModel> photos;

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

  @JsonFormat(pattern = "MM/dd/yyyy")
  private LocalDate startDate;

  @JsonFormat(pattern = "MM/dd/yyyy")
  private LocalDate endDate;

  @Nullable
  private String coupon;

  // Provável mudança para criar uma tabela intermediária
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerModel customer;

  // @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  // private List<RatingModel> ratings;
}
