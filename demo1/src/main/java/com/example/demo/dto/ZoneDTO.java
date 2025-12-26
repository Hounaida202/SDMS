package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ZoneDTO {
    private Long id;

    @NotBlank(message = "Le nom de la zone est obligatoire")
    @Size(max = 100)
    private String nom;

    @Size(max = 20)
    private String codePostal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
}