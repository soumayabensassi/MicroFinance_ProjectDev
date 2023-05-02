package com.example.microgrowth.DAO.Entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.Set;


@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @ToString
@Component
    public class Investment implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(unique = true)
        int idInvestment;
    @NotNull(message = "Le montant d'investissement ne doit pas être vide")
    @DecimalMin(value = "0.0", message = "Le montant d'investissement doit être supérieur à 0")
        double amountInves;
        int  duree;


        @Enumerated(EnumType.STRING)
        MethodInvestissement methodInvestissement;
        @Temporal(TemporalType.DATE)
        Date DateInv = new Date();

    @JsonIgnore
        @ManyToOne
        User users;
    @JsonIgnore
        @ManyToMany()
        private Set<Projet> projets;


}
