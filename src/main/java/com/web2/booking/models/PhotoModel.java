package com.web2.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "photo")
@Data
public class PhotoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductModel product;

    @NotBlank
    private String path;

    @NotBlank
    private String subtitle;
}
