package com.web2.booking.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.booking.DTO.Customer.CreateCustomerInputDTO;
import com.web2.booking.DTO.Customer.CreateCustomerOutputDTO;
import com.web2.booking.DTO.Customer.CustomerOutputDTO;
import com.web2.booking.DTO.Customer.DeleteCustomerOutputDTO;
import com.web2.booking.DTO.Customer.UpdateCustomerInputDTO;
import com.web2.booking.models.CustomerModel;
import com.web2.booking.repositories.CustomerRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class CustomerService {
  @Autowired
  private CustomerRepository customerReposity;

  @Autowired
  private Validator validator;

  public CustomerOutputDTO getCustomer(UUID id) {
    CustomerModel customer = customerReposity.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    return mapCustomerToCustomerOutputDTO(customer);
  }

  public CreateCustomerOutputDTO saveCustomer(CreateCustomerInputDTO input) {
    CustomerModel newCustomer = new CustomerModel();
    BeanUtils.copyProperties(input, newCustomer);
    newCustomer.setCreatedAt(LocalDateTime.now());

    validateCustomer(newCustomer);

    CustomerModel savedCustomer = customerReposity.save(newCustomer);

    CreateCustomerOutputDTO output = new CreateCustomerOutputDTO(
        savedCustomer.getId(),
        savedCustomer.getFirstName(),
        savedCustomer.getLastName(),
        savedCustomer.getEmail(),
        savedCustomer.getCreatedAt());

    return output;
  }

  public CustomerOutputDTO updateCustomer(UUID id, UpdateCustomerInputDTO customer) {
    CustomerModel customerToUpdate = customerReposity.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer not found"));

    BeanUtils.copyProperties(customer, customerToUpdate, getNullPropertyNames(customer));
    validateCustomer(customerToUpdate);
    CustomerModel updatedCustomer = customerReposity.save(customerToUpdate);
    return mapCustomerToCustomerOutputDTO(updatedCustomer);
  }

  public DeleteCustomerOutputDTO deleteCustomer(UUID id) {
    customerReposity.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
    customerReposity.deleteById(id);
    return new DeleteCustomerOutputDTO(true);
  }

  private void validateCustomer(CustomerModel customer) {
    Set<ConstraintViolation<CustomerModel>> violations = validator.validate(customer);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
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

  private CustomerOutputDTO mapCustomerToCustomerOutputDTO(CustomerModel customer) {
    return new CustomerOutputDTO(
        customer.getId(),
        customer.getFirstName(),
        customer.getLastName(),
        customer.getEmail(),
        customer.getPhoneNumber(),
        customer.getCpf(),
        customer.getDateOfBirth(),
        customer.getNationality(),
        customer.getGender(),
        customer.getCreatedAt());
  }

}
