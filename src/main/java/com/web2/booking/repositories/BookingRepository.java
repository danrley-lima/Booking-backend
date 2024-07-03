package com.web2.booking.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.booking.models.BookingModel;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, UUID> {
    List<BookingModel> getBookingsByCustomerId(UUID customerId);

    @Query("SELECT COUNT(b) > 0 FROM BookingModel b WHERE b.customer.id = :customerId AND b.product.id = :productId")
    boolean existsByCustomerIdAndProductId(@Param("customerId") UUID customerId, @Param("productId") UUID productId);
}
