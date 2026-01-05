/*
package com.example.demo.controller;

import com.example.demo.dto.ClientExpediteurDTO;
import com.example.demo.service.ClientExpediteurService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientExpediteurController.class)
class ClientExpediteurControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClientExpediteurService service;

    @Test
    void creer_DevraitRetourner201_QuandDonneesValides() throws Exception {
        // Arrange
        ClientExpediteurDTO inputDto = new ClientExpediteurDTO();
        inputDto.setNom("Dupont");
        inputDto.setPrenom("Jean");
        inputDto.setEmail("jean@example.com");
        inputDto.setTelephone("0612345678");

        ClientExpediteurDTO outputDto = new ClientExpediteurDTO();
        outputDto.setId(1L);
        outputDto.setNom("Dupont");
        outputDto.setPrenom("Jean");
        outputDto.setEmail("jean@example.com");
        outputDto.setTelephone("0612345678");

        when(service.creer(any(ClientExpediteurDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"))
                .andExpect(jsonPath("$.email").value("jean@example.com"));

        verify(service, times(1)).creer(any(ClientExpediteurDTO.class));
    }

    @Test
    void creer_DevraitRetourner400_QuandNomManquant() throws Exception {
        ClientExpediteurDTO inputDto = new ClientExpediteurDTO();
        inputDto.setPrenom("Jean");
        inputDto.setEmail("jean@example.com");

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());

        verify(service, never()).creer(any());
    }

    @Test
    void creer_DevraitRetourner400_QuandEmailInvalide() throws Exception {
        ClientExpediteurDTO inputDto = new ClientExpediteurDTO();
        inputDto.setNom("Dupont");
        inputDto.setPrenom("Jean");
        inputDto.setEmail("email-invalide");

        mockMvc.perform(post("/api/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());

        verify(service, never()).creer(any());
    }

    @Test
    void getById_DevraitRetourner200_QuandClientExiste() throws Exception {
        Long id = 1L;
        ClientExpediteurDTO dto = new ClientExpediteurDTO();
        dto.setId(id);
        dto.setNom("Dupont");
        dto.setPrenom("Jean");

        when(service.getById(id)).thenReturn(dto);

        mockMvc.perform(get("/api/clients/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Dupont"))
                .andExpect(jsonPath("$.prenom").value("Jean"));

        verify(service, times(1)).getById(id);
    }

    @Test
    void rechercherParNom_DevraitRetourner200_QuandClientsExistent() throws Exception {
        String nom = "Dupont";
        ClientExpediteurDTO dto1 = new ClientExpediteurDTO();
        dto1.setId(1L);
        dto1.setNom("Dupont");
        dto1.setPrenom("Jean");

        ClientExpediteurDTO dto2 = new ClientExpediteurDTO();
        dto2.setId(2L);
        dto2.setNom("Dupont");
        dto2.setPrenom("Marie");

        List<ClientExpediteurDTO> clients = Arrays.asList(dto1, dto2);
        when(service.rechercherParNom(nom)).thenReturn(clients);

        mockMvc.perform(get("/api/clients/recherche")
                        .param("nom", nom))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[1].nom").value("Dupont"));

        verify(service, times(1)).rechercherParNom(nom);
    }

    @Test
    void rechercherParNom_DevraitRetournerListeVide_QuandAucunClient() throws Exception {
        String nom = "NomInexistant";
        when(service.rechercherParNom(nom)).thenReturn(Arrays.asList());

        mockMvc.perform(get("/api/clients/recherche")
                        .param("nom", nom))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(service, times(1)).rechercherParNom(nom);
    }
}*/
