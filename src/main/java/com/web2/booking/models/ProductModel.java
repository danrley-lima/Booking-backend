package com.web2.booking.models;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "product")
@Data
public class ProductModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  private String title;
  private String city;
  private String state;
  private String image;
  private double price;
  private double customerScore;
  private int numberOfReviews;
  private int discount;
  private double totalPrice;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

}
