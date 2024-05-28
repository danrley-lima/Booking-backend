package com.web2.booking.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.br.CNPJ;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "establishment")
@Data
public class EstablishmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne
    private UserProfileModel userProfileModel;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private AddressModel addressModel;

    @NotBlank
    private String description;

    @NotBlank
    @Size(min = 14, max = 14)
    @CNPJ
    private String cnpj;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<ProductModel> products;
}
