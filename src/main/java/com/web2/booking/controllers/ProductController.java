package com.web2.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.booking.DTO.CreateProductInputDTO;
import com.web2.booking.DTO.CreateProductOutputDTO;
import com.web2.booking.services.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
  @Autowired
  ProductService productService;

  @PostMapping("")
  ResponseEntity<CreateProductOutputDTO> createProduct(@RequestBody CreateProductInputDTO product) {
    CreateProductOutputDTO response = productService.saveProduct(product);
    return ResponseEntity.ok(response);
  }
}
