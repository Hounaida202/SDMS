package com.example.demo.service;

import com.example.demo.dto.ProduitDTO;
import com.example.demo.entity.Produit;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProduitMapper;
import com.example.demo.repository.ProduitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProduitService {

    private static final Logger logger = LoggerFactory.getLogger(ProduitService.class);

    @Autowired
    private ProduitRepository repository;

    @Autowired
    private ProduitMapper mapper;

    public ProduitDTO creer(ProduitDTO dto) {
        logger.info("Création d'un produit: {}", dto.getNom());
        Produit entity = mapper.toEntity(dto);
        Produit saved = repository.save(entity);
        logger.info("Produit créé avec ID={}", saved.getId());
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public ProduitDTO getById(Long id) {
        logger.info("Récupération du produit ID={}", id);
        Produit entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit", "id", id));
        return mapper.toDto(entity);
    }
}