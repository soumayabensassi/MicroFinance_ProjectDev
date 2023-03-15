package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Penalite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPenalite;
    @Temporal(TemporalType.DATE)
    Date datePenalite;
    float montant_penalite;
    boolean paye;
    @ManyToOne
    @JsonIgnore
    Credit credits;

}
