package com.web2.booking.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.web2.booking.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, UUID> {
  // @Query("SELECT p FROM ProductModel p WHERE " +
  // "(:category IS NULL OR p.category = :category) AND " +
  // "(:saindoDe IS NULL OR p.saindoDe = :saindoDe) AND " +
  // "(:indoPara IS NULL OR p.indoPara = :indoPara) AND " +
  // "(:data IS NULL OR p.data = :data) AND " +
  // "(:pessoas IS NULL OR p.pessoas = :pessoas)")
  // List<ProductModel> searchProducts(String category, String saindoDe, String
  // indoPara, String data, Integer pessoas);

  // @Query("SELECT p FROM product p " +
  // "WHERE (:date IS NULL OR p.startDate <= :date) " +
  // "AND (:date IS NULL OR p.endDate >= :date)")
  // List<ProductModel> findByDateBetweenStartDateAndEndDate(LocalDate date);

  @Query("SELECT p FROM ProductModel p WHERE " +
      "(:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', LOWER(:title), '%'))) AND " +
      "(:city IS NULL OR LOWER(p.city) LIKE LOWER(CONCAT('%', LOWER(:city), '%'))) AND " +
      "(:startDate IS NULL OR p.startDate >= :startDate) AND " +
      "(:category IS NULL OR LOWER(p.category) = LOWER(:category))")
  List<ProductModel> searchProducts(@Param("title") String title, @Param("city") String city,
      @Param("startDate") LocalDate startDate, @Param("category") String category);

  @Query("SELECT p FROM ProductModel p WHERE " +
      "(:title IS NULL OR LOWER(p.title) LIKE LOWER(CONCAT('%', LOWER(:title), '%'))) AND " +
      "(:city IS NULL OR LOWER(p.city) LIKE LOWER(CONCAT('%', LOWER(:city), '%')))")
  List<ProductModel> searchProducts(@Param("title") String title, @Param("city") String city);
}
