package com.web2.booking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.web2.booking.models.UsersModel;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(UsersModel usersModel) {
        return JWT.create()
                .withIssuer("Booking-api")
                .withSubject(usersModel.getUsername())
                .withClaim("id", String.valueOf(usersModel.getId()))
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-3:00"))
                ).sign(Algorithm.HMAC256("booking"));
    }
}
