package com.web2.booking.DTO.Establishment;


import com.web2.booking.models.AddressModel;
import com.web2.booking.models.UserProfileModel;

public record CreateEstablishmentInputDTO(UserProfileModel userProfileModel, AddressModel addressModel, String description, String cnpj) {
    
}
