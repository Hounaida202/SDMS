package com.example.demo.controller;


import com.example.demo.dto.ColisDTO;
import com.example.demo.service.ColisService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/colis")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @PostMapping
    public ResponseEntity<ColisDTO> creerColis(@Valid @RequestBody ColisDTO dto) {
        ColisDTO created = colisService.creerColis(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColisDTO> getColisById(@PathVariable Long id) {
        ColisDTO colis = colisService.getColisById(id);
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<ColisDTO>> getColisByClient(
            @PathVariable Long clientId,
            Pageable pageable) {
        Page<ColisDTO> colis = colisService.getColisByClient(clientId, pageable);
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/livreur/{livreurId}")
    public ResponseEntity<List<ColisDTO>> getColisByLivreur(@PathVariable Long livreurId) {
        List<ColisDTO> colis = colisService.getColisByLivreur(livreurId);
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/sans-livreur")
    public ResponseEntity<List<ColisDTO>> getColisSansLivreur() {
        List<ColisDTO> colis = colisService.getColisSansLivreur();
        return ResponseEntity.ok(colis);
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<ColisDTO> updateStatut(
            @PathVariable Long id,
            @RequestParam String statut) {
        ColisDTO updated = colisService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{colisId}/livreur/{livreurId}")
    public ResponseEntity<ColisDTO> assignerLivreur(
            @PathVariable Long colisId,
            @PathVariable Long livreurId) {
        ColisDTO updated = colisService.assignerLivreur(colisId, livreurId);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/filtrer")
    public ResponseEntity<Page<ColisDTO>> filtrerColis(
            @RequestParam List<String> statuts,
            @RequestParam Long zoneId,
            @RequestParam(required = false) String ville,
            @RequestParam List<String> priorites,
            Pageable pageable) {
        Page<ColisDTO> colis = colisService.filtrerColis(statuts, zoneId, ville, priorites, pageable);
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/livreur/{livreurId}/statistiques/nombre")
    public ResponseEntity<Long> compterColisParLivreur(@PathVariable Long livreurId) {
        long count = colisService.compterColisParLivreur(livreurId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/livreur/{livreurId}/statistiques/poids")
    public ResponseEntity<BigDecimal> calculerPoidsTotalParLivreur(@PathVariable Long livreurId) {
        BigDecimal poids = colisService.calculerPoidsTotalParLivreur(livreurId);
        return ResponseEntity.ok(poids);
    }
}
