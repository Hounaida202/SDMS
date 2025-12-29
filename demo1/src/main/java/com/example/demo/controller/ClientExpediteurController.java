package com.example.demo.controller;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.service.ClientExpediteurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientExpediteurController {

    private final ClientExpediteurService service;

    public ClientExpediteurController(ClientExpediteurService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<ClientExpediteurDTO> creer(@Valid @RequestBody ClientExpediteurDTO dto) {
        ClientExpediteurDTO created = service.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}