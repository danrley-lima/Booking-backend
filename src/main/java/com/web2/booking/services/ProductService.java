package com.web2.booking.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.web2.booking.models.EstablishmentModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
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
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

@Service
public class ProductService {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private ProductRepository productRepository;

  @Autowired
  private EstablishmentService establishmentService;

  @Autowired
  private Validator validator;

  // public List<ProductOutputDTO> searchProducts(String category, String
  // saindoDe, String indoPara, String data,
  // Integer pessoas) {
  // Category enumCategory = category != null ?
  // Category.valueOf(category.toUpperCase()) : null;
  // LocalDate date = data != null ? LocalDate.parse(data) : null;

  // List<ProductModel> products = productRepository.searchProducts(enumCategory,
  // saindoDe, indoPara, date, pessoas);
  // List<ProductOutputDTO> productDTOs = new ArrayList<>();

  // for (ProductModel product : products) {
  // ProductOutputDTO productDTO = new ProductOutputDTO();
  // // Mapear os campos do model para o DTO
  // productDTO.setId(product.getId());
  // productDTO.setTitle(product.getName());
  // // Adicione os demais campos necessários
  // productDTOs.add(productDTO);
  // }

  // return productDTOs;
  // }

  public List<ProductModel> searchProductsByDate(LocalDate data) {
    return null;

    // List<ProductModel> products =
    // productRepository.findByDateBetweenStartDateAndEndDate(data);
    // List<ProductModel> productsModels = new ArrayList<>();

    // for (ProductModel product : products) {
    // ProductModel productDTO = new ProductModel();
    // productDTO.setId(product.getId());
    // productDTO.setName(product.getName());
    // productDTO.setCity(product.getCity());
    // productDTO.setState(product.getState());
    // productDTO.setPrice(product.getPrice());
    // productDTO.setCustomerScore(product.getCustomerScore());
    // productDTO.setNumberOfReviews(product.getNumberOfReviews());
    // productDTO.setDiscount(product.getDiscount());
    // productDTO.setCoupon(product.getCoupon());
    // productDTO.setStartDate(product.getStartDate());
    // productDTO.setEndDate(product.getEndDate());
    // productDTO.setCreatedAt(product.getCreatedAt());
    // productDTO.setMainImage(product.getMainImage());
    // productDTO.setPhotos(product.getPhotos());
    // productDTO.setCategory(product.getCategory());

    // productsModels.add(productDTO);
    // }

    // return productsModels;
  }

  public CreateProductOutputDTO saveProduct(CreateProductInputDTO product) {
    ProductModel newProduct = new ProductModel();
    BeanUtils.copyProperties(product, newProduct);
    newProduct.setCreatedAt(LocalDateTime.now());

    validateProduct(newProduct);

    ProductModel savedProduct = productRepository.save(newProduct);

    EstablishmentModel e = establishmentService.findByUserModelId(idUser);
    e.getProducts().add(savedProduct);
    entityManager.merge(e);

    CreateProductOutputDTO output = new CreateProductOutputDTO(savedProduct.getId(),
        savedProduct.getTitle(), savedProduct.getDescription(), savedProduct.getMainImage(),
        savedProduct.getCity(), savedProduct.getState(), savedProduct.getPrice(),
        savedProduct.getCustomerScore(), savedProduct.getNumberOfReviews(),
        savedProduct.getDiscount(), savedProduct.getCreatedAt(),
        savedProduct.getStartDate(), savedProduct.getEndDate(), savedProduct.getCategory().toString(),
        savedProduct.getQuantity());

    return output;
  }

  public ProductOutputDTO getProduct(UUID id) {
    ProductModel product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));

    return mapProductToProductOutputDTO(product);
  }

  public List<ProductOutputDTO> findAll() {
    List<ProductModel> list = productRepository.findAll();
    List<ProductOutputDTO> products = new ArrayList<>();
    for (ProductModel product : list) {
      products.add(mapProductToProductOutputDTO(product));
    }
    return products;
  }

  public DeleteProductOutputDTO deleteProduct(UUID id) {
    productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    productRepository.deleteById(id);
    return new DeleteProductOutputDTO(true);
  }

  public ProductOutputDTO updateProduct(UUID id, UpdateProductInputDTO product) {
    ProductModel productToUpdate = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Product not found"));

    BeanUtils.copyProperties(product, productToUpdate, getNullPropertyNames(product));
    validateProduct(productToUpdate);
    ProductModel updatedProduct = productRepository.save(productToUpdate);
    return mapProductToProductOutputDTO(updatedProduct);
  }

  private ProductOutputDTO mapProductToProductOutputDTO(ProductModel product) {
    return new ProductOutputDTO(product.getId(), product.getTitle(), product.getDescription(),
        product.getMainImage(), product.getCity(), product.getState(), product.getPrice(),
        product.getCustomerScore(), product.getNumberOfReviews(), product.getDiscount(), product.getCreatedAt(),
        product.getStartDate(), product.getEndDate(), product.getCategory(), product.getQuantity());
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

  private void validateProduct(ProductModel product) {
    Set<ConstraintViolation<ProductModel>> violations = validator.validate(product);
    if (!violations.isEmpty()) {
      throw new ConstraintViolationException(violations);
    }
  }
}
