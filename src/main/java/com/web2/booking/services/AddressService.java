package com.web2.booking.services;

import com.web2.booking.models.AddressModel;
import com.web2.booking.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressModel save(AddressModel addressModel) {
        return addressRepository.save(addressModel);
    }

    public AddressModel mapToModel(AddressModel addressModel) {
        AddressModel address = new AddressModel();
        address.setCity(addressModel.getCity());
        address.setState(addressModel.getState());
        address.setStreetAddress(addressModel.getStreetAddress());
        address.setZipCode(addressModel.getZipCode());
        address.setNeighborhood(addressModel.getNeighborhood());
        address.setNumber(addressModel.getNumber());
        address.setComplement(addressModel.getComplement());
        return addressModel;
    }
}
