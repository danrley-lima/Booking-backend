package com.web2.booking.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
    // @Size(min = 8, max = 8)
    // @Pattern(regexp = "\\d{5}-\\d{3}", message = "The zip code must be in the
    // format 12345-678")
    private String zipCode;

    @NotBlank
    private String streetAddress;

    @NotBlank
    private String number;

    @NotBlank
    private String neighborhood;

    private String complement;
}
