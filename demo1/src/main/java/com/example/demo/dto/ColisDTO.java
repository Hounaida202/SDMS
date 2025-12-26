package com.example.demo.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ColisDTO {
    private Long id;

    @NotBlank(message = "La description est obligatoire")
    @Size(max = 255)
    private String description;

    @NotNull(message = "Le poids est obligatoire")
    @DecimalMin(value = "0.01", message = "Le poids doit être supérieur à 0")
    private BigDecimal poids;

    @NotBlank(message = "Le statut est obligatoire")
    @Pattern(regexp = "crée|collecté|en stock|en transit|livré",
            message = "Statut invalide")
    private String statut;

    @NotBlank(message = "La priorité est obligatoire")
    @Pattern(regexp = "P1|P2|P3", message = "Priorité invalide (P1, P2 ou P3)")
    private String priorite;

    @NotBlank(message = "La ville de destination est obligatoire")
    @Size(max = 100)
    private String villeDestination;

    private Long livreurId;
    private String livreurNom;

    @NotNull(message = "Le client expéditeur est obligatoire")
    private Long clientExpediteurId;
    private String clientExpediteurNom;

    @NotNull(message = "Le destinataire est obligatoire")
    private Long destinataireId;
    private String destinataireNom;

    @NotNull(message = "La zone est obligatoire")
    private Long zoneId;
    private String zoneNom;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getPoids() { return poids; }
    public void setPoids(BigDecimal poids) { this.poids = poids; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getPriorite() { return priorite; }
    public void setPriorite(String priorite) { this.priorite = priorite; }

    public String getVilleDestination() { return villeDestination; }
    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    public Long getLivreurId() { return livreurId; }
    public void setLivreurId(Long livreurId) { this.livreurId = livreurId; }

    public String getLivreurNom() { return livreurNom; }
    public void setLivreurNom(String livreurNom) { this.livreurNom = livreurNom; }

    public Long getClientExpediteurId() { return clientExpediteurId; }
    public void setClientExpediteurId(Long clientExpediteurId) {
        this.clientExpediteurId = clientExpediteurId;
    }

    public String getClientExpediteurNom() { return clientExpediteurNom; }
    public void setClientExpediteurNom(String clientExpediteurNom) {
        this.clientExpediteurNom = clientExpediteurNom;
    }

    public Long getDestinataireId() { return destinataireId; }
    public void setDestinataireId(Long destinataireId) {
        this.destinataireId = destinataireId;
    }

    public String getDestinataireNom() { return destinataireNom; }
    public void setDestinataireNom(String destinataireNom) {
        this.destinataireNom = destinataireNom;
    }

    public Long getZoneId() { return zoneId; }
    public void setZoneId(Long zoneId) { this.zoneId = zoneId; }

    public String getZoneNom() { return zoneNom; }
    public void setZoneNom(String zoneNom) { this.zoneNom = zoneNom; }
}
