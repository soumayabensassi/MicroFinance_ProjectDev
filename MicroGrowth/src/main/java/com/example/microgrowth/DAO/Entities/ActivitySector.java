package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class ActivitySector implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int idSecteur;
        String name;
        String image_sector;
    @OneToMany(mappedBy = "activiteSecteurs")
            @JsonIgnore
    List<Credit> creditList;
}
