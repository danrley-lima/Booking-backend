package com.web2.booking.DTO.Establishment;


import com.web2.booking.models.AddressModel;
import com.web2.booking.models.UserModel;

public record CreateEstablishmentInputDTO(UserModel userModel, AddressModel addressModel, String description, String cnpj) {
    
}
