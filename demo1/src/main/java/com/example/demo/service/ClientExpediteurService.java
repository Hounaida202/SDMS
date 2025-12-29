package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ClientExpediteurMapper;
import com.example.demo.repository.ClientExpediteurRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Data
@NoArgsConstructor
@Slf4j
public class ClientExpediteurService {

    private static final Logger logger = LoggerFactory.getLogger(ClientExpediteurService.class);

    @Autowired
    private ClientExpediteurRepository repository;

    @Autowired
    private ClientExpediteurMapper mapper;

    public ClientExpediteurDTO creer(ClientExpediteurDTO dto) {
        logger.info("Création d'un client expéditeur: {}", dto.getNom());

        ClientExpediteur client = mapper.toEntity(dto);
        ClientExpediteur saved = repository.save(client);

        logger.info("Client créé avec ID={}", saved.getId());
        return mapper.toDto(saved);
    }

    public ClientExpediteurDTO getById(Long id) {
        logger.info("Récupération du client ID={}", id);
        ClientExpediteur client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", "id", id));
        return mapper.toDto(client);
    }

}
