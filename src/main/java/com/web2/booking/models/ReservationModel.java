package com.web2.booking.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "reservation")
@Data
public class ReservationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Positive
    private double price;

    @NotNull
    @Positive
    private int quantity;

    private boolean status = true;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private RatingModel ratingModel;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate startDate;

    @JsonFormat(pattern = "MM/dd/yyyy")
    private LocalDate endDate;
}
