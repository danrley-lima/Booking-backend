package com.web2.booking.services;

import com.web2.booking.DTO.Login.AuthenticationDTO;
import com.web2.booking.DTO.Login.LoginResponseDTO;
import com.web2.booking.DTO.Login.RegisterDTO;
import com.web2.booking.models.UserModel;
import com.web2.booking.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    private AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO dto) {
        authenticationManager = context.getBean(AuthenticationManager.class);
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((UserModel) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token, ((UserModel) auth.getPrincipal()).getId(), ((UserModel) auth.getPrincipal()).getRole()));
    }

    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(userRepository.findByEmail(registerDTO.email()) != null) return ResponseEntity.badRequest().build();
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        UserModel newUser = new UserModel(registerDTO.email(), encryptedPassword, registerDTO.role(), registerDTO.name(), registerDTO.phoneNumber());
        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
