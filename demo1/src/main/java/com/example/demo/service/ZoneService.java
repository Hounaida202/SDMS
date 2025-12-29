package com.example.demo.service;

import com.example.demo.dto.ZoneDTO;
import com.example.demo.entity.Zone;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ZoneMapper;
import com.example.demo.repository.ZoneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ZoneService {

    private static final Logger logger = LoggerFactory.getLogger(ZoneService.class);

    @Autowired
    private ZoneRepository repository;

    @Autowired
    private ZoneMapper mapper;

    public ZoneDTO creer(ZoneDTO dto) {
        logger.info("Création d'une zone: {}", dto.getNom());
        Zone entity = mapper.toEntity(dto);
        Zone saved = repository.save(entity);
        logger.info("Zone créée avec ID={}", saved.getId());
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public ZoneDTO getById(Long id) {
        logger.info("Récupération de la zone ID={}", id);
        Zone entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", id));
        return mapper.toDto(entity);
    }
}
