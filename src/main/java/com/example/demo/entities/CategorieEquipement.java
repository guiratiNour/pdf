package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
public class CategorieEquipement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categorie_id;

    private String nom;

    @OneToMany(mappedBy = "categorie", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Equipement> equipements ;
}
