package com.web2.booking.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web2.booking.DTO.Product.CreateProductInputDTO;
import com.web2.booking.DTO.Product.CreateProductOutputDTO;
import com.web2.booking.DTO.Product.DeleteProductOutputDTO;
import com.web2.booking.DTO.Product.ProductOutputDTO;
import com.web2.booking.DTO.Product.UpdateProductInputDTO;
import com.web2.booking.models.ProductModel;
import com.web2.booking.repositories.ProductRepository;

@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;

  public CreateProductOutputDTO saveProduct(CreateProductInputDTO input) {
    ProductModel newProduct = new ProductModel();
    BeanUtils.copyProperties(input, newProduct);
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

  public ProductOutputDTO getProduct(UUID id) {
    ProductModel product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    return mapProductToProductOutput(product);
  }

  public DeleteProductOutputDTO deleteProduct(UUID id) {
    productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));
    productRepository.deleteById(id);
    return new DeleteProductOutputDTO(true);
  }

  public ProductOutputDTO updateProduct(UUID id, UpdateProductInputDTO input) {
    ProductModel productModel = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    BeanUtils.copyProperties(input, productModel, getNullPropertyNames(input));
    ProductModel updatedProduct = productRepository.save(productModel);
    return mapProductToProductOutput(updatedProduct);
  }

  private ProductOutputDTO mapProductToProductOutput(ProductModel product) {
    return new ProductOutputDTO(
        product.getId(),
        product.getTitle(),
        product.getCity(),
        product.getState(),
        product.getImage(),
        product.getPrice(),
        product.getCustomerScore(),
        product.getNumberOfReviews(),
        product.getDiscount(),
        product.getTotalPrice(),
        product.getCreatedAt());
  }

  private String[] getNullPropertyNames(Object source) {
    final BeanWrapper src = new BeanWrapperImpl(source);
    java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    for (java.beans.PropertyDescriptor pd : pds) {
      Object srcValue = src.getPropertyValue(pd.getName());
      if (srcValue == null)
        emptyNames.add(pd.getName());
    }
    String[] result = new String[emptyNames.size()];
    return emptyNames.toArray(result);
  }
}
