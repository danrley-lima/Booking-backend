package com.web2.booking.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import com.web2.booking.DTO.Establishment.CreateEstablishmentInputDTO;
import com.web2.booking.DTO.Establishment.CreateEstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.DeleteEstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.EstablishmentOutputDTO;
import com.web2.booking.DTO.Establishment.UpdateEstablishmentDTO;
import com.web2.booking.services.EstablishmentService;

@RestController
@RequestMapping("api/establishment")
public class EstablishmentController {
    @Autowired
    EstablishmentService establishmentService;

    @GetMapping
    public ResponseEntity<List<EstablishmentOutputDTO>> getAllEstablishments(){
        List<EstablishmentOutputDTO> response = establishmentService.getAllEstablishments();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentOutputDTO> getEstablishmentById(@PathVariable UUID id) {
        EstablishmentOutputDTO response = establishmentService.getEstablishment(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/")
    public ResponseEntity<CreateEstablishmentOutputDTO> createEstablishment(@RequestBody CreateEstablishmentInputDTO establishment) {
        CreateEstablishmentOutputDTO response = establishmentService.saveEstablishment(establishment);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentOutputDTO> updateEstablishment(@PathVariable UUID id, @RequestBody UpdateEstablishmentDTO establishment){
        EstablishmentOutputDTO response = establishmentService.updateEstablishment(id, establishment);
        return ResponseEntity.ok(response);
    }

    @DeleteExchange("/{id}")
    public ResponseEntity<DeleteEstablishmentOutputDTO> deleteEstablishment(@PathVariable UUID id){
        DeleteEstablishmentOutputDTO response = establishmentService.deleteEstablishment(id);
        return ResponseEntity.ok(response);
    }
}
