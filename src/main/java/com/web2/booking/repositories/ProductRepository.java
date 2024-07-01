package com.web2.booking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.booking.enums.Category;
import com.web2.booking.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

  @Query("SELECT p FROM ProductModel p WHERE :date BETWEEN p.startDate AND p.endDate " +
      "AND (:category IS NULL OR p.category = :category)")
  List<ProductModel> findProductsByDateAndCategory(
      @Param("date") LocalDate date,
      @Param("category") Category category);

  @Query("SELECT p FROM ProductModel p WHERE :date BETWEEN p.startDate AND p.endDate")
  List<ProductModel> findProductsByDate(
      @Param("date") LocalDate date);

  @Query("SELECT p FROM ProductModel p WHERE (:startDate IS NULL OR p.startDate >= :startDate) AND (:endDate IS NULL OR p.endDate <= :endDate)")
  List<ProductModel> findProductsByDateRange(
      @Param("startDate") LocalDate startDate,
      @Param("endDate") LocalDate endDate);
}
