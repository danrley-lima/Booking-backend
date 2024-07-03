package com.web2.booking.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Bookings")
@Data
public class BookingModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private CustomerModel customer;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private ProductModel product;

  @Column(name = "booking_date")
  private LocalDateTime bookingDate;

  @Column(name = "start_date")
  private LocalDateTime startDate;

  @Column(name = "end_date")
  private LocalDateTime endDate;

  @Column(name = "total_price")
  private double totalPrice;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "booking_users", joinColumns = @JoinColumn(name = "booking_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<UserModel> users;
}
