package com.web2.booking.DTO.Login;

import com.web2.booking.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(@NotNull String email, @NotNull String password, @NotNull Role role, @NotNull String name, @NotNull String phoneNumber) {
}
