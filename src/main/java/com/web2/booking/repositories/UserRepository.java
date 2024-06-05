package com.web2.booking.repositories;

import com.web2.booking.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UsersModel, UUID> {

    UsersModel findByEmailAndPassword(String email, String password);

    UsersModel findByEmail(String email);

}
