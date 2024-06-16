package com.web2.booking.DTO.Establishment;

import java.util.List;

import com.web2.booking.models.AddressModel;
import com.web2.booking.models.ProductModel;

public record UpdateEstablishmentDTO(AddressModel addressModel, String description, List<ProductModel> products) {
    
}
