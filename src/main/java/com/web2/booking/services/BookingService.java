package com.web2.booking.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web2.booking.DTO.BookingResponseDto;
import com.web2.booking.DTO.CustomerDto;
import com.web2.booking.DTO.ProductDto;
import com.web2.booking.models.BookingModel;
import com.web2.booking.models.CustomerModel;
import com.web2.booking.models.ProductModel;
import com.web2.booking.repositories.BookingRepository;
import com.web2.booking.repositories.CustomerRepository;
import com.web2.booking.repositories.ProductRepository;
import com.web2.booking.repositories.UserRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Transactional(readOnly = true)
	public List<BookingModel> getBookingsByCustomerId(UUID customerId) {
		return bookingRepository.getBookingsByCustomerId(customerId);
	}

	@Transactional
	public BookingResponseDto createBooking(UUID customerId, UUID productId, LocalDateTime startDate,
			LocalDateTime endDate, double totalPrice) {
		CustomerModel customer = customerRepository.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
		ProductModel product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Produto não encontrado."));

		if (bookingRepository.existsByCustomerIdAndProductId(customerId, productId)) {
			throw new IllegalStateException("Usuário já fez uma reserva nesse produto.");
		}

		BookingModel booking = new BookingModel();
		booking.setCustomer(customer);
		booking.setProduct(product);
		booking.setStartDate(startDate);
		booking.setEndDate(endDate);
		booking.setTotalPrice(totalPrice);
		booking.setBookingDate(LocalDateTime.now());

		booking = bookingRepository.save(booking);

		// Mapear entidades para DTOs
		BookingResponseDto bookingResponseDto = new BookingResponseDto();
		bookingResponseDto.setId(booking.getId());
		bookingResponseDto.setBookingDate(booking.getBookingDate());
		bookingResponseDto.setStartDate(booking.getStartDate());
		bookingResponseDto.setEndDate(booking.getEndDate());
		bookingResponseDto.setTotalPrice(booking.getTotalPrice());

		CustomerDto customerDto = new CustomerDto();
		customerDto.setId(customer.getId());
		customerDto.setFirstName(customer.getFirstName());
		customerDto.setLastName(customer.getLastName());
		customerDto.setEmail(customer.getEmail());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		bookingResponseDto.setCustomer(customerDto);

		ProductDto productDto = new ProductDto();
		productDto.setId(product.getId());
		productDto.setTitle(product.getTitle());
		productDto.setDescription(product.getDescription());
		productDto.setPrice(product.getPrice());
		bookingResponseDto.setProduct(productDto);

		return bookingResponseDto;
	}
}
