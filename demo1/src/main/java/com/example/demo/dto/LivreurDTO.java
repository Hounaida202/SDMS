package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LivreurDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    @Size(max = 100)
    private String prenom;

    @Size(max = 20)
    private String telephone;

    @Size(max = 50)
    private String vehicule;

    private Long zoneAssigneeId;
    private String zoneAssigneeNom;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }

    public String getVehicule() { return vehicule; }
    public void setVehicule(String vehicule) { this.vehicule = vehicule; }

    public Long getZoneAssigneeId() { return zoneAssigneeId; }
    public void setZoneAssigneeId(Long zoneAssigneeId) { this.zoneAssigneeId = zoneAssigneeId; }

    public String getZoneAssigneeNom() { return zoneAssigneeNom; }
    public void setZoneAssigneeNom(String zoneAssigneeNom) { this.zoneAssigneeNom = zoneAssigneeNom; }
}

