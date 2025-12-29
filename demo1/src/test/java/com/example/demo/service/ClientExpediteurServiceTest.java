package com.example.demo.service;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.entity.ClientExpediteur;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ClientExpediteurMapper;
import com.example.demo.repository.ClientExpediteurRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientExpediteurServiceTest {

    @Mock
    private ClientExpediteurRepository repository;

    @Mock
    private ClientExpediteurMapper mapper;

    @InjectMocks
    private ClientExpediteurService service;

    @Test
    void creer_DevraitCreerUnClient_QuandDonneesValides() {

        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setNom("ahmad");
        dto.setPrenom("ahmad");
        dto.setEmail("ahmad@gmail.com");

        ClientExpediteur entity = new ClientExpediteur();
        entity.setNom("ahmad");
        entity.setPrenom("ahmad");

        ClientExpediteur savedEntity = new ClientExpediteur();
        savedEntity.setId(1L);
        savedEntity.setNom("ahmad");
        savedEntity.setPrenom("ahmad");

        ClientExpediteurDTO savedDto = new ClientExpediteurDTO();
        savedDto.setId(1L);
        savedDto.setNom("ahmad");
        savedDto.setPrenom("ahmad");

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(savedEntity);
        when(mapper.toDto(savedEntity)).thenReturn(savedDto);

        ClientExpediteurDTO result = service.creer(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("ahmad", result.getNom());
        assertEquals("ahmad", result.getPrenom());
        verify(repository, times(1)).save(entity);
    }

    @Test
    void getById_DevraitRetournerClient_QuandIdExiste() {

        Long id = 1L;
        ClientExpediteur entity = new ClientExpediteur();
        entity.setId(id);
        entity.setNom("ahmad");
        entity.setPrenom("ahmad");

        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setId(id);
        dto.setNom("ahmad");
        dto.setPrenom("ahmad");

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        ClientExpediteurDTO result = service.getById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("ahmad", result.getNom());
        verify(repository, times(1)).findById(id);
    }

    @Test
    void getById_DevraitLancerException_QuandIdNexistePas() {

        Long id = 999L;
        when(repository.findById(id)).thenReturn(Optional.empty());


        assertThrows(ResourceNotFoundException.class, () -> service.getById(id));
        verify(repository, times(1)).findById(id);
    }

    @Test
    void rechercherParNom_DevraitRetournerListeClients_QuandNomExiste() {

        String nom = "ahmad";
        ClientExpediteur entity1 = new ClientExpediteur();
        entity1.setId(1L);
        entity1.setNom("ahmad");
        entity1.setPrenom("ahmad");

        ClientExpediteur entity2 = new ClientExpediteur();
        entity2.setId(2L);
        entity2.setNom("ahmad");
        entity2.setPrenom("ahmad");

        ClientExpediteurDTO dto1 = new ClientExpediteurDTO();
        dto1.setId(1L);
        dto1.setNom("ahmad");

        ClientExpediteurDTO dto2 = new ClientExpediteurDTO();
        dto2.setId(2L);
        dto2.setNom("ahmad");

        when(repository.findByNomIgnoreCase(nom)).thenReturn(Arrays.asList(entity1, entity2));
        when(mapper.toDto(entity1)).thenReturn(dto1);
        when(mapper.toDto(entity2)).thenReturn(dto2);

        List<ClientExpediteurDTO> result = service.rechercherParNom(nom);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findByNomIgnoreCase(nom);
    }

    @Test
    void rechercherParNom_DevraitRetournerListeVide_QuandNomNexistePas() {

        String nom = "nom";
        when(repository.findByNomIgnoreCase(nom)).thenReturn(Arrays.asList());


        List<ClientExpediteurDTO> result = service.rechercherParNom(nom);


        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findByNomIgnoreCase(nom);
    }
}