package com.web2.booking.models;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "establishment")
@Data
public class EstablishmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private UserModel userModel;

    @Nullable
    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private AddressModel addressModel;

    @NotBlank
    private String description;

    @NotBlank
    @Size(min = 14, max = 14)
    private String cnpj;

    @JsonIgnore
    @Nullable
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ProductModel> products;
}
