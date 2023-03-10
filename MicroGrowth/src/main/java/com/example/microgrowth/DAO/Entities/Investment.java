package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;


@Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @ToString
    public class Investment implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int idInvestment;
    double amountInves;
        int  duree;
        @Enumerated(EnumType.STRING)
        MethodInvestissement methodInvestissement;
        @Temporal(TemporalType.DATE)
        Date DateInv = new Date();
        @ManyToOne
        User users;


}
