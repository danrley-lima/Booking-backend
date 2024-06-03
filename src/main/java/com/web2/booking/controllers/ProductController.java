package com.web2.booking.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web2.booking.DTO.Product.CreateProductInputDTO;
import com.web2.booking.DTO.Product.CreateProductOutputDTO;
import com.web2.booking.DTO.Product.DeleteProductOutputDTO;
import com.web2.booking.DTO.Product.ProductOutputDTO;
import com.web2.booking.models.ProductModel;
import com.web2.booking.services.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
  @Autowired
  ProductService productService;

  @GetMapping("/search")
  public ResponseEntity<List<ProductModel>> searchProducts(
      @RequestParam(value = "title", required = false) String title,
      @RequestParam(value = "city", required = false) String city,
      @RequestParam(value = "startDate", required = false) LocalDate startDate,
      @RequestParam(value = "category", required = false) String category) {

    List<ProductModel> response = productService.searchProducts(title, city, startDate, category);

    return ResponseEntity.ok(response);
  }

  @GetMapping("")
  public ResponseEntity<List<ProductModel>> getAllProducts() {
    List<ProductModel> response = productService.getAllProducts();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductOutputDTO> getProductById(@PathVariable UUID id) {
    ProductOutputDTO response = productService.getProduct(id);
    return ResponseEntity.ok(response);
  }

  @PostMapping("")
  public ResponseEntity<CreateProductOutputDTO> createProduct(@RequestBody CreateProductInputDTO product) {
    CreateProductOutputDTO response = productService.saveProduct(product);
    return ResponseEntity.ok(response);
  }

  // @PutMapping("/{id}")
  // ResponseEntity<ProductOutputDTO> updateProduct(@PathVariable UUID id,
  // @RequestBody UpdateProductInputDTO product) {
  // ProductOutputDTO response = productService.updateProduct(id, product);
  // return ResponseEntity.ok(response);
  // }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteProductOutputDTO> deleteProductById(@PathVariable UUID id) {

    DeleteProductOutputDTO response = productService.deleteProduct(id);
    return ResponseEntity.ok(response);
  }

}
