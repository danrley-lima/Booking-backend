package com.web2.booking.controllers;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.booking.DTO.BookingDto;
import com.web2.booking.DTO.Customer.CreateCustomerInputDTO;
import com.web2.booking.DTO.Customer.CreateCustomerOutputDTO;
import com.web2.booking.DTO.Customer.CustomerOutputDTO;
import com.web2.booking.DTO.Customer.DeleteCustomerOutputDTO;
import com.web2.booking.DTO.Customer.UpdateCustomerInputDTO;
import com.web2.booking.models.BookingModel;
import com.web2.booking.services.BookingService;
import com.web2.booking.services.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomerController {
  @Autowired
  CustomerService customerService;

  @Autowired
  BookingService bookingService;

  @GetMapping("/{customerId}/bookings")
  public ResponseEntity<List<BookingDto>> getBookingsByCustomerId(@PathVariable UUID customerId) {
    List<BookingModel> bookings = bookingService.getBookingsByCustomerId(customerId);
    List<BookingDto> bookingDTOs = bookings.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    return ResponseEntity.ok(bookingDTOs);
  }

  @GetMapping
  public ResponseEntity<List<CustomerOutputDTO>> getCustomers() {
    List<CustomerOutputDTO> response = customerService.getCustomers();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CustomerOutputDTO> getCustomerById(@PathVariable UUID id) {
    CustomerOutputDTO response = customerService.getCustomer(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping
  public ResponseEntity<CreateCustomerOutputDTO> createCustomer(@RequestBody CreateCustomerInputDTO customer) {
    CreateCustomerOutputDTO response = customerService.saveCustomer(customer);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CustomerOutputDTO> updateCustomer(@PathVariable UUID id,
      @RequestBody UpdateCustomerInputDTO customer) {
    CustomerOutputDTO response = customerService.updateCustomer(id, customer);
    return ResponseEntity.ok(response);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteCustomerOutputDTO> deleteCustomerById(@PathVariable UUID id) {
    DeleteCustomerOutputDTO response = customerService.deleteCustomer(id);
    return ResponseEntity.ok(response);
  }

  private BookingDto convertToDTO(BookingModel booking) {
    BookingDto dto = new BookingDto();
    dto.setId(booking.getId());
    dto.setStartDate(booking.getStartDate());
    dto.setEndDate(booking.getEndDate());
    // Mapear outros campos conforme necessário
    return dto;
  }
}
