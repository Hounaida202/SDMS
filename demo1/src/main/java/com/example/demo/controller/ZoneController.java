package com.example.demo.controller;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    @Autowired
    private ZoneService service;

    @PostMapping
    public ResponseEntity<ZoneDTO> creer(@Valid @RequestBody ZoneDTO dto) {
        ZoneDTO created = service.creer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ZoneDTO> getById(@PathVariable Long id) {
        ZoneDTO zone = service.getById(id);
        return ResponseEntity.ok(zone);
    }
}
