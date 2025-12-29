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

    @Transactional(readOnly = true)
    public Page<ColisDTO> getColisByClient(Long clientId, Pageable pageable) {
        logger.info("Récupération des colis du client ID={}", clientId);
        return colisRepository.findByIdClientExpediteur_Id(clientId, pageable)
                .map(colisMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<ColisDTO> getColisByLivreur(Long livreurId) {
        logger.info("Récupération des colis du livreur ID={}", livreurId);
        return colisRepository.findByIdLivreur_Id(livreurId)
                .stream()
                .map(colisMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ColisDTO> getColisSansLivreur() {
        logger.info("Récupération des colis sans livreur");
        return colisRepository.findByIdLivreurIsNull()
                .stream()
                .map(colisMapper::toDto)
                .toList();
    }

    public ColisDTO updateStatut(Long id, String nouveauStatut) {
        logger.info("Mise à jour du statut du colis ID={} vers '{}'", id, nouveauStatut);

        Colis colis = colisRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Colis", "id", id));

        colis.setStatut(nouveauStatut);
        Colis updated = colisRepository.save(colis);

        return colisMapper.toDto(updated);
    }

    public ColisDTO assignerLivreur(Long colisId, Long livreurId) {
        logger.info("Assignation du livreur ID={} au colis ID={}", livreurId, colisId);

        Colis colis = colisRepository.findById(colisId)
                .orElseThrow(() -> new ResourceNotFoundException("Colis", "id", colisId));

        if (colis.getIdLivreur() != null) {
            throw new BusinessException("Ce colis est déjà assigné à un livreur");
        }

        Livreur livreur = livreurRepository.findById(livreurId)
                .orElseThrow(() -> new ResourceNotFoundException("Livreur", "id", livreurId));

        colis.setIdLivreur(livreur);
        Colis updated = colisRepository.save(colis);

        return colisMapper.toDto(updated);
    }

    @Transactional(readOnly = true)
    public Page<ColisDTO> filtrerColis(List<String> statuts, Long zoneId,
                                       String ville, List<String> priorites,
                                       Pageable pageable) {
        logger.info("Filtrage des colis");
        return colisRepository
                .findByStatutInAndIdZone_IdAndVilleDestinationContainingIgnoreCaseAndPrioriteIn(
                        statuts, zoneId, ville != null ? ville : "", priorites, pageable)
                .map(colisMapper::toDto);
    }

    @Transactional(readOnly = true)
    public long compterColisParLivreur(Long livreurId) {
        logger.info("Comptage des colis du livreur ID={}", livreurId);
        return colisRepository.countByIdLivreur_Id(livreurId);
    }

    @Transactional(readOnly = true)
    public BigDecimal calculerPoidsTotalParLivreur(Long livreurId) {
        logger.info("Calcul du poids total pour le livreur ID={}", livreurId);
        return colisRepository.getPoidsTotalParLivreur(livreurId);
    }
}