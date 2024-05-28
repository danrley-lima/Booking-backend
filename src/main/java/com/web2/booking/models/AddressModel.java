package com.web2.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "address")
@Data
public class AddressModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    @Size(min = 8, max = 8)
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "The zip code must be in the format 12345-678")
    private String zipCode;

    @NotBlank
    private String streetAddress;

    @NotBlank
    private String number;

    @NotBlank
    private String neighborhood;

    private String complement;
}
