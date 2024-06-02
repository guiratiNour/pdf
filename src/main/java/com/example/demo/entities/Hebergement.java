package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hebergement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hebergement_id;
    private String nom;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    private String ville;
    private String pays;
    private double prix;
    private String distance;
    private String phone;
    private String email;
    private String adresse;
    private String politiqueAnnulation;
    private String nbEtoile;
    private double superficie;
    private int nb_Salles_De_Bains;
    private int nb_Chambres;

    private String website;
    private String facebook;
    private String instagram;
    private String fax;
    private String country_code;
    private String currency;
    private String cancellationfees;

    @ManyToOne
    private Categorie categorie;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "hebergement_equipement",
            joinColumns = @JoinColumn(name = "hebergement_id"),
            inverseJoinColumns = @JoinColumn(name = "equipement_id"))
    @JsonIgnore
    private List<Equipement> equipements;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "hebergement_language",
            joinColumns = @JoinColumn(name = "hebergement_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<language> languages;
    
    @OneToMany(mappedBy = "hebergement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chambre> chambres;

    @OneToMany(mappedBy = "hebergement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Position> positions;

    @OneToMany(mappedBy = "hebergement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "hebergement", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Dates> reservationDates;
}
