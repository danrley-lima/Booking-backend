package com.web2.booking.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "customer")
@Data
public class CustomerModel {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @NotBlank
  @Size(max = 50)
  private String firstName;

  @NotBlank
  @Size(max = 50)
  private String lastName;

  @NotBlank
  @Email
  @Column(unique = true)
  private String email;

  @Pattern(regexp = "^\\(\\d{2}\\)\\s\\d{4,5}-\\d{4}$", message = "Invalid phone number format")
  private String phoneNumber;

  @NotBlank
  @Size(min = 6, max = 20)
  private String password;

  @Size(min = 11, max = 11)
  @Pattern(regexp = "[0-9]{11}")
  @Column(unique = true)
  private String cpf;

  private LocalDate dateOfBirth;

  private String nationality;

  @Pattern(regexp = "^(masculino|feminino|outros)$", message = "Gênero inválido")
  private String gender;

  
  @Column(name = "created_at")
  private LocalDateTime createdAt;
  
  @OneToMany(mappedBy = "customer",  cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<ProductModel> favorites;

  @OneToMany(mappedBy = "customer",  cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
  private List<ProductModel> history; 

  // @OneToMany(mappedBy = "customer")
  // private List<ProductModel> schedule; // Lista de agendamentos futuros 

}
