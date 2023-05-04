package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class Intreview implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int idIntreview;
        @Temporal(TemporalType.DATE)
        Date dateIntreview;
    @OneToOne(mappedBy = "intreviews")
    @JsonIgnore
    Credit credits;
}
