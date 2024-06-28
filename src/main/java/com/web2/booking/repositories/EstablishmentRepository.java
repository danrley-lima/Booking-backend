package com.web2.booking.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.booking.models.EstablishmentModel;

@Repository
public interface EstablishmentRepository extends JpaRepository<EstablishmentModel, UUID>{

    EstablishmentModel findByUserModelId(UUID idUser);
}
