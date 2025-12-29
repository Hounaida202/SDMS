package com.example.demo.controller;


import com.example.demo.dto.DestinataireDTO;
import com.example.demo.service.DestinataireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/destinataires")
public class DestinataireController {

    @Autowired
    private DestinataireService service;

    @PostMapping
    public ResponseEntity<DestinataireDTO> creer(@Valid @RequestBody DestinataireDTO dto) {
        DestinataireDTO created = service.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DestinataireDTO> getById(@PathVariable Long id) {
        DestinataireDTO destinataire = service.getById(id);
        return ResponseEntity.ok(destinataire);
    }
}