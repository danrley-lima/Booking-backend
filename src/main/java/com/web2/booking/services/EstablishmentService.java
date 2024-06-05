package com.web2.booking.services;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.booking.DTO.Establishment.CreateEstablishmentInputDTO;
import com.web2.booking.DTO.Establishment.CreateEstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.DeleteEstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.EstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.UpdateEstablishmentDTO;
import com.web2.booking.models.EstablishmentModel;
import com.web2.booking.repositories.EstablishmentRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class EstablishmentService {

    @Autowired
    private EstablishmentRepository establishmentRepository;

    @Autowired
    private Validator validator;
    
    public EstablishmentOutputDTO getEstablishment(UUID id){
        EstablishmentModel establishment = establishmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Establishment not found"));
        return mapEstablishmentToEstablishmentDTO(establishment);
    }

    public CreateEstablishmentOutputDTO saveEstablishment(CreateEstablishmentInputDTO input){
        EstablishmentModel newEstablishment = new EstablishmentModel();
        BeanUtils.copyProperties(input, newEstablishment);

        validateEstablishment(newEstablishment);

        EstablishmentModel savedEstablishment = establishmentRepository.save(newEstablishment);

        return new CreateEstablishmentOutputDTO(
            savedEstablishment.getId(),
            savedEstablishment.getUserProfileModel(),
            savedEstablishment.getAddressModel(),
            savedEstablishment.getDescription(),
            savedEstablishment.getCnpj(),
            savedEstablishment.getProducts()
        );
    }

    public EstablishmentOutputDTO updateEstablishment(UUID id, UpdateEstablishmentDTO establishment){
        EstablishmentModel establishmentToUpdate = establishmentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Establishment not found"));

        validateEstablishment(establishmentToUpdate);

        BeanUtils.copyProperties(establishment, establishmentToUpdate, getNullPropertyNames(establishmentToUpdate));
        EstablishmentModel updatedEstablishment = establishmentRepository.save(establishmentToUpdate);
        return mapEstablishmentToEstablishmentDTO(updatedEstablishment);
    }

    public DeleteEstablishmentOutputDTO deleteEstablishment(UUID id){
        establishmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Establishment not found"));
        establishmentRepository.deleteById(id);
        return new DeleteEstablishmentOutputDTO(true);
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
        Object srcValue = src.getPropertyValue(pd.getName());
        if (srcValue == null)
            emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    private void validateEstablishment(EstablishmentModel establishment) {
        Set<ConstraintViolation<EstablishmentModel>> violations = validator.validate(establishment);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private EstablishmentOutputDTO mapEstablishmentToEstablishmentDTO(EstablishmentModel establishment) {
    return new EstablishmentOutputDTO(
        establishment.getId(),
        establishment.getUserProfileModel(),
        establishment.getAddressModel(),
        establishment.getDescription(),
        establishment.getCnpj(),
        establishment.getProducts());
  }
}
