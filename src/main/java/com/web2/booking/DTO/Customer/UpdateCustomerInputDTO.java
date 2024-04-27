package com.web2.booking.DTO.Customer;

import java.time.LocalDate;

public record UpdateCustomerInputDTO(String firstName, String lastName, String email, String phoneNumber,
    String password, String cpf, LocalDate dateOfBirth, String nationality, String gender) {

}
