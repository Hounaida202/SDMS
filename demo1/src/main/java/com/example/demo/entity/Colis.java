package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

@Entity
@Table(name = "colis")
public class Colis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @DecimalMin(value = "0.01")
    @Column(name = "poids", precision = 10, scale = 2)
    private BigDecimal poids;

    @NotBlank
    @Size(max = 50)
    @Column(name = "statut", length = 50)
    private String statut;

    @NotBlank
    @Column(name = "priorite", length = 20)
    private String priorite;

    @NotBlank
    @Size(max = 100)
    @Column(name = "ville_destination", length = 100)
    private String villeDestination;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_livreur")
    private Livreur idLivreur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_client_expediteur")
    private ClientExpediteur idClientExpediteur;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_destinataire")
    private Destinataire idDestinataire;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zone")
    private Zone idZone;

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

    public Livreur getIdLivreur() { return idLivreur; }
    public void setIdLivreur(Livreur idLivreur) { this.idLivreur = idLivreur; }

    public ClientExpediteur getIdClientExpediteur() { return idClientExpediteur; }
    public void setIdClientExpediteur(ClientExpediteur idClientExpediteur) {
        this.idClientExpediteur = idClientExpediteur;
    }

    public Destinataire getIdDestinataire() { return idDestinataire; }
    public void setIdDestinataire(Destinataire idDestinataire) {
        this.idDestinataire = idDestinataire;
    }

    public Zone getIdZone() { return idZone; }
    public void setIdZone(Zone idZone) { this.idZone = idZone; }
}
