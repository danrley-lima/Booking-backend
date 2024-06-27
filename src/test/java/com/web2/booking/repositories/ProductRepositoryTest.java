package com.web2.booking.repositories;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.web2.booking.models.ProductModel;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ProductRepositoryTest {

  private ProductModel product;

  @BeforeEach
  public void setUp() {
    product = new ProductModel();
    product.setTitle("Test Product");
    product.setPrice(100.0);
    product.setCity("Test City");
    product.setDiscount(20);
    product.setMainImage("blablabla");
    product.setNumberOfReviews(12);
    product.setState("RN");
    // product.setTotalPrice(3200);
    product.setCustomerScore(8.5);
    product.setCreatedAt(LocalDateTime.of(2024, 01, 20, 0, 0));
  }

  @Autowired
  private ProductRepository productRepository;

  @Test
  @DisplayName("Save Product Test")
  void testSaveProduct() {
    ProductModel savedProduct = productRepository.save(product);
    Assertions.assertThat(savedProduct).isNotNull();
    Assertions.assertThat(savedProduct.getId()).isNotNull();
  }
}
