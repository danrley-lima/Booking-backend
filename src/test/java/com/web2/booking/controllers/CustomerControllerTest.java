package com.web2.booking.controllers;

import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web2.booking.DTO.Customer.CreateCustomerInputDTO;
import com.web2.booking.DTO.Customer.CreateCustomerOutputDTO;
import com.web2.booking.DTO.Customer.CustomerOutputDTO;
import com.web2.booking.services.CustomerService;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    public void setUp() {}

    @Test
    void testGetCustomerByIdCase1() throws Exception {
        UUID customerId = UUID.randomUUID();
        CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO(customerId, "Jon", "Snow",
                "JonSnow@test.com", null, null, null, null, null, LocalDateTime.now());

        when(customerService.getCustomer(customerId)).thenReturn(customerOutputDTO);

        mockMvc.perform(
                get("/api/customers/{id}", customerId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.firstName").value(customerOutputDTO.firstName()))
                .andExpect(jsonPath("$.lastName").value(customerOutputDTO.lastName()))
                .andExpect(jsonPath("$.email").value(customerOutputDTO.email()))
                .andExpect(jsonPath("$.phoneNumber").value(nullValue()))
                .andExpect(jsonPath("$.CPF").value(nullValue()))
                .andExpect(jsonPath("$.dateOfBirth").value(nullValue()))
                .andExpect(jsonPath("$.nationality").value(nullValue()))
                .andExpect(jsonPath("$.gender").value(nullValue()))
                .andExpect(jsonPath("$.createdAt").value(customerOutputDTO.createdAt().toString()));

        verify(customerService, times(1)).getCustomer(customerId);
    }

    @Test
    void testGetCustomerByIdCase2() throws Exception {
        UUID customerId = UUID.randomUUID();
        CustomerOutputDTO customerOutputDTO = new CustomerOutputDTO(customerId, "Arya", "Stark",
                "AryaStark@test.com", "123456789", "123.456.789-00", LocalDate.of(2000, 1, 1),
                "Braavosi", "FEMALE", LocalDateTime.now());

        when(customerService.getCustomer(customerId)).thenReturn(customerOutputDTO);

        mockMvc.perform(
                get("/api/customers/{id}", customerId).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.firstName").value("Arya"))
                .andExpect(jsonPath("$.lastName").value("Stark"))
                .andExpect(jsonPath("$.email").value("AryaStark@test.com"))
                .andExpect(jsonPath("$.phoneNumber").value("123456789"))
                .andExpect(jsonPath("$.CPF").value("123.456.789-00"))
                .andExpect(jsonPath("$.dateOfBirth").value("2000-01-01"))
                .andExpect(jsonPath("$.nationality").value("Braavosi"))
                .andExpect(jsonPath("$.gender").value("FEMALE"))
                .andExpect(jsonPath("$.createdAt").value(customerOutputDTO.createdAt().toString()));
    }

    @Test
    void testCreateCustomer() throws Exception {
        CreateCustomerInputDTO createCustomerInputDTO =
                new CreateCustomerInputDTO("Jon", "Snow", "JonSnow@test.com", "123123123");
        UUID customerId = UUID.randomUUID();
        LocalDateTime customerCreatedAt = LocalDateTime.now();

        when(customerService.saveCustomer(createCustomerInputDTO))
                .thenReturn(new CreateCustomerOutputDTO(customerId, "Jon", "Snow",
                        "JonSnow@test.com", customerCreatedAt));

        mockMvc.perform(post("/api/customers/").contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(createCustomerInputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(customerId.toString()))
                .andExpect(jsonPath("$.firstName").value("Jon"))
                .andExpect(jsonPath("$.lastName").value("Snow"))
                .andExpect(jsonPath("$.email").value("JonSnow@test.com"))
                .andExpect(jsonPath("$.createdAt").value(customerCreatedAt.toString()));
    }
}
