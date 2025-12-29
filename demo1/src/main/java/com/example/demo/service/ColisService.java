package com.example.demo.service;


import com.example.demo.dto.ColisDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ColisMapper;
import com.example.demo.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class ColisService {

    private static final Logger logger = LoggerFactory.getLogger(ColisService.class);

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private ClientExpediteurRepository clientRepository;

    @Autowired
    private DestinataireRepository destinataireRepository;

    @Autowired
    private ZoneRepository zoneRepository;

    @Autowired
    private LivreurRepository livreurRepository;

    @Autowired
    private ColisMapper colisMapper;

    public ColisDTO creerColis(ColisDTO dto) {
        logger.info("Création d'un nouveau colis");

        Colis colis = colisMapper.toEntity(dto);

        ClientExpediteur client = clientRepository.findById(dto.getClientExpediteurId())
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", dto.getClientExpediteurId()));
        colis.setIdClientExpediteur(client);

        Destinataire destinataire = destinataireRepository.findById(dto.getDestinataireId())
                .orElseThrow(() -> new ResourceNotFoundException("Destinataire", "id", dto.getDestinataireId()));
        colis.setIdDestinataire(destinataire);

        Zone zone = zoneRepository.findById(dto.getZoneId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone", "id", dto.getZoneId()));
        colis.setIdZone(zone);

        Colis saved = colisRepository.save(colis);
        logger.info("Colis créé avec succès, ID={}", saved.getId());

        return colisMapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public ColisDTO getColisById(Long id) {
        logger.info("Récupération du colis ID={}", id);
        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis", "id", id));
        return colisMapper.toDto(colis);
    }


}