package com.example.demo.controller;


import com.example.demo.dto.ProduitDTO;
import com.example.demo.service.ProduitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produits")
public class ProduitController {

    @Autowired
    private ProduitService service;

    @PostMapping
    public ResponseEntity<ProduitDTO> creer(@Valid @RequestBody ProduitDTO dto) {
        ProduitDTO created = service.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProduitDTO> getById(@PathVariable Long id) {
        ProduitDTO produit = service.getById(id);
        return ResponseEntity.ok(produit);
    }
}
