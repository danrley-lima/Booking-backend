package com.web2.booking.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web2.booking.DTO.BookingRequestDto;
import com.web2.booking.DTO.BookingResponseDto;
import com.web2.booking.services.BookingService;

@RestController
@RequestMapping("api/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingRequestDto bookingRequestDto) {
        BookingResponseDto booking = bookingService.createBooking(
                bookingRequestDto.getCustomerId(),
                bookingRequestDto.getProductId(),
                bookingRequestDto.getStartDate(),
                bookingRequestDto.getEndDate(),
                bookingRequestDto.getTotalPrice());
        return ResponseEntity.ok(booking);
    }

}
