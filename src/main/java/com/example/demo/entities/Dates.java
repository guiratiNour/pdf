package com.example.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Entity
public class Dates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long date_id;
    private String startDate;
    private String endDate;
    @ManyToOne
    @JoinColumn(name = "chambre_id")
    private Chambre chambre;
    @ManyToOne
    @JoinColumn(name = "hebergement_id")
    private Hebergement hebergement;

}
