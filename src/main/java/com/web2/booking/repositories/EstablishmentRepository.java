package com.web2.booking.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.web2.booking.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.booking.models.EstablishmentModel;

@Repository
public interface EstablishmentRepository extends JpaRepository<EstablishmentModel, UUID>{

    EstablishmentModel findByUserModelId(UUID idUser);

    @Query("SELECT e.products FROM EstablishmentModel e WHERE e.id = :establishmentId")
    List<ProductModel> findProductsByEstablishmentId(@Param("establishmentId") UUID establishmentId);

}
