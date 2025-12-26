package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 100)
    @Column(name = "nom", length = 100)
    private String nom;

    @Size(max = 20)
    @Column(name = "code_postal", length = 20)
    private String codePostal;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getCodePostal() { return codePostal; }
    public void setCodePostal(String codePostal) { this.codePostal = codePostal; }
}
