package com.web2.booking.services;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.booking.DTO.CreateProductInputDTO;
import com.web2.booking.DTO.CreateProductOutputDTO;
import com.web2.booking.models.ProductModel;
import com.web2.booking.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public CreateProductOutputDTO saveProduct(CreateProductInputDTO product) {
    ProductModel newProduct = new ProductModel();
    BeanUtils.copyProperties(product, newProduct);
    newProduct.setCreatedAt(LocalDateTime.now());
    ProductModel savedProduct = productRepository.save(newProduct);

    CreateProductOutputDTO output = new CreateProductOutputDTO(
        savedProduct.getId(),
        savedProduct.getTitle(),
        savedProduct.getCity(),
        savedProduct.getState(),
        savedProduct.getImage(),
        savedProduct.getPrice(),
        savedProduct.getCustomerScore(),
        savedProduct.getNumberOfReviews(),
        savedProduct.getDiscount(),
        savedProduct.getTotalPrice(),
        savedProduct.getCreatedAt());

    return output;
  }
}
