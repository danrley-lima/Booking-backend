package com.web2.booking.models;

import com.web2.booking.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
public class UserModel implements UserDetails {

    public UserModel() {}

    public UserModel(String email, String encryptedPassword, Role role, String name, String phoneNumber) {
        this.email = email;
        this.password = encryptedPassword;
        this.role = role;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == Role.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_CUSTOMER"), new SimpleGrantedAuthority("ROLE_ESTABLISHMENT"));
        } else if(this.role == Role.CUSTOMER) {
            return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_ESTABLISHMENT"));
        }
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
