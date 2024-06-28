package com.web2.booking.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.web2.booking.enums.Category;
import com.web2.booking.models.ProductModel;

@Component
public class DatabaseSeeder implements CommandLineRunner {

  @Autowired
  private ProductRepository productRepository;

  @Override
  public void run(String... args) throws Exception {
    seedDatabase();
  }

  private void seedDatabase() {
    // Limpa o banco de dados para evitar duplicação durante o desenvolvimento
   /* productRepository.deleteAll();

    ProductModel product1 = new ProductModel();
    product1.setTitle("Product 1");
    product1.setDescription("Description for Product 1");
    product1.setMainImage("image1.jpg");
    product1.setCity("City 1");
    product1.setState("State 1");
    product1.setPrice(100.0);
    product1.setCustomerScore(4.5);
    product1.setNumberOfReviews(10);
    product1.setDiscount(10);
    product1.setCreatedAt(LocalDateTime.now());
    product1.setStartDate(LocalDate.now());
    product1.setEndDate(LocalDate.now().plusDays(7));
    product1.setCoupon("DISCOUNT10");
    product1.setCategory(Category.PACKAGES);
    product1.setQuantity(4);

    ProductModel product2 = new ProductModel();
    product2.setTitle("Product 2");
    product2.setDescription("Description for Product 2");
    product2.setMainImage("image2.jpg");
    product2.setCity("City 2");
    product2.setState("State 2");
    product2.setPrice(200.0);
    product2.setCustomerScore(4.0);
    product2.setNumberOfReviews(20);
    product2.setDiscount(15);
    product2.setCreatedAt(LocalDateTime.now());
    product2.setStartDate(LocalDate.now());
    product2.setEndDate(LocalDate.now().plusDays(14));
    product2.setCoupon("DISCOUNT15");
    product2.setCategory(Category.ACCOMMODATION);
    product2.setQuantity(4);

    ProductModel product3 = new ProductModel();
    product3.setTitle("Product 3");
    product3.setDescription("Description for Product 3");
    product3.setMainImage("image3.jpg");
    product3.setCity("City 3");
    product3.setState("State 3");
    product3.setPrice(300.0);
    product3.setCustomerScore(4.8);
    product3.setNumberOfReviews(30);
    product3.setDiscount(20);
    product3.setCreatedAt(LocalDateTime.now());
    product3.setStartDate(LocalDate.now());
    product3.setEndDate(LocalDate.now().plusDays(21));
    product3.setCoupon("DISCOUNT20");
    product3.setCategory(Category.RESTAURANT);
    product3.setQuantity(4);

    ProductModel product4 = new ProductModel();
    product4.setTitle("Product 4");
    product4.setDescription("Description for Product 4");
    product4.setMainImage("image4.jpg");
    product4.setCity("City 4");
    product4.setState("State 4");
    product4.setPrice(400.0);
    product4.setCustomerScore(4.7);
    product4.setNumberOfReviews(40);
    product4.setDiscount(25);
    product4.setCreatedAt(LocalDateTime.now());
    product4.setStartDate(LocalDate.now());
    product4.setEndDate(LocalDate.now().plusDays(28));
    product4.setCoupon("DISCOUNT25");
    product4.setCategory(Category.ACCOMMODATION);
    product4.setQuantity(4);

    // Salva os produtos no banco de dados
    productRepository.saveAll(Arrays.asList(product1, product2, product3, product4));

    System.out.println("Database seeded successfully.");*/
  }
}