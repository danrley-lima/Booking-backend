package com.web2.booking.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web2.booking.models.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, UUID> {

}
