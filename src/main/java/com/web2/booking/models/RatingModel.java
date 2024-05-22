package com.web2.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "rating")
@Data
public class RatingModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Positive
    @NotNull
    private int rate;

    @NotBlank
    private String comment;
}
