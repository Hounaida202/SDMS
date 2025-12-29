package com.example.demo.service;

import com.example.demo.dto.ColisDTO;
import com.example.demo.entity.*;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ColisMapper;
import com.example.demo.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ColisServiceTest {

    @Mock
    private ColisRepository colisRepository;

    @Mock
    private ClientExpediteurRepository clientRepository;

    @Mock
    private DestinataireRepository destinataireRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private LivreurRepository livreurRepository;

    @Mock
    private ColisMapper colisMapper;

    @InjectMocks
    private ColisService service;

    @Test
    void creerColis_DevraitCreerColis_QuandDonneesValides() {

        ColisDTO dto = new ColisDTO();
        dto.setDescription("Colis test");
        dto.setPoids(BigDecimal.valueOf(5.0));
        dto.setStatut("crée");
        dto.setPriorite("P1");
        dto.setClientExpediteurId(1L);
        dto.setDestinataireId(2L);
        dto.setZoneId(3L);

        Colis entity = new Colis();
        ClientExpediteur client = new ClientExpediteur();
        client.setId(1L);
        Destinataire destinataire = new Destinataire();
        destinataire.setId(2L);
        Zone zone = new Zone();
        zone.setId(3L);

        Colis savedEntity = new Colis();
        savedEntity.setId(1L);

        ColisDTO savedDto = new ColisDTO();
        savedDto.setId(1L);

        when(colisMapper.toEntity(dto)).thenReturn(entity);
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
        when(destinataireRepository.findById(2L)).thenReturn(Optional.of(destinataire));
        when(zoneRepository.findById(3L)).thenReturn(Optional.of(zone));
        when(colisRepository.save(any(Colis.class))).thenReturn(savedEntity);
        when(colisMapper.toDto(savedEntity)).thenReturn(savedDto);


        ColisDTO result = service.creerColis(dto);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(colisRepository, times(1)).save(any(Colis.class));
    }

    @Test
    void creerColis_DevraitLancerException_QuandClientNexistePas() {

        ColisDTO dto = new ColisDTO();
        dto.setClientExpediteurId(999L);
        dto.setDestinataireId(2L);
        dto.setZoneId(3L);

        when(colisMapper.toEntity(dto)).thenReturn(new Colis());
        when(clientRepository.findById(999L)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> service.creerColis(dto));
        verify(colisRepository, never()).save(any());
    }

    @Test
    void getColisById_DevraitRetournerColis_QuandIdExiste() {

        Long id = 1L;
        Colis entity = new Colis();
        entity.setId(id);

        ColisDTO dto = new ColisDTO();
        dto.setId(id);

        when(colisRepository.findById(id)).thenReturn(Optional.of(entity));
        when(colisMapper.toDto(entity)).thenReturn(dto);

        ColisDTO result = service.getColisById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(colisRepository, times(1)).findById(id);
    }

    @Test
    void updateStatut_DevraitMettreAJourStatut_QuandColisExiste() {

        Long id = 1L;
        String nouveauStatut = "livré";
        Colis entity = new Colis();
        entity.setId(id);
        entity.setStatut("en transit");

        ColisDTO dto = new ColisDTO();
        dto.setId(id);
        dto.setStatut(nouveauStatut);

        when(colisRepository.findById(id)).thenReturn(Optional.of(entity));
        when(colisRepository.save(entity)).thenReturn(entity);
        when(colisMapper.toDto(entity)).thenReturn(dto);

        ColisDTO result = service.updateStatut(id, nouveauStatut);

        assertNotNull(result);
        assertEquals(nouveauStatut, result.getStatut());
        verify(colisRepository, times(1)).save(entity);
    }

    @Test
    void assignerLivreur_DevraitAssignerLivreur_QuandColisNonAssigne() {
        Long colisId = 1L;
        Long livreurId = 2L;

        Colis colis = new Colis();
        colis.setId(colisId);
        colis.setIdLivreur(null);

        Livreur livreur = new Livreur();
        livreur.setId(livreurId);

        ColisDTO dto = new ColisDTO();
        dto.setId(colisId);
        dto.setLivreurId(livreurId);

        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));
        when(livreurRepository.findById(livreurId)).thenReturn(Optional.of(livreur));
        when(colisRepository.save(colis)).thenReturn(colis);
        when(colisMapper.toDto(colis)).thenReturn(dto);

        ColisDTO result = service.assignerLivreur(colisId, livreurId);

        assertNotNull(result);
        assertEquals(livreurId, result.getLivreurId());
        verify(colisRepository, times(1)).save(colis);
    }

    @Test
    void assignerLivreur_DevraitLancerException_QuandColisDejaAssigne() {
        Long colisId = 1L;
        Long livreurId = 2L;

        Livreur livreurExistant = new Livreur();
        livreurExistant.setId(99L);

        Colis colis = new Colis();
        colis.setId(colisId);
        colis.setIdLivreur(livreurExistant);

        when(colisRepository.findById(colisId)).thenReturn(Optional.of(colis));

        assertThrows(BusinessException.class, () -> service.assignerLivreur(colisId, livreurId));
        verify(colisRepository, never()).save(any());
    }

    @Test
    void compterColisParLivreur_DevraitRetournerNombre_QuandLivreurADesColis() {
        Long livreurId = 1L;
        long expectedCount = 5L;

        when(colisRepository.countByIdLivreur_Id(livreurId)).thenReturn(expectedCount);

        long result = service.compterColisParLivreur(livreurId);

        assertEquals(expectedCount, result);
        verify(colisRepository, times(1)).countByIdLivreur_Id(livreurId);
    }

    @Test
    void calculerPoidsTotalParLivreur_DevraitRetournerPoids_QuandLivreurADesColis() {
        Long livreurId = 1L;
        BigDecimal expectedPoids = BigDecimal.valueOf(25.5);

        when(colisRepository.getPoidsTotalParLivreur(livreurId)).thenReturn(expectedPoids);

        BigDecimal result = service.calculerPoidsTotalParLivreur(livreurId);

        assertEquals(expectedPoids, result);
        verify(colisRepository, times(1)).getPoidsTotalParLivreur(livreurId);
    }
}