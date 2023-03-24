package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Projet implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idprojet;
    private String nom;
    private String description;
    private Double obligation;

    private Integer duree;
    private Double tauxRendement;
    private String statutProjet;
    private String secteurActivite;
    @ManyToMany(mappedBy="projets")
    @JsonIgnore
    private Set<Investment> Investments;


}
