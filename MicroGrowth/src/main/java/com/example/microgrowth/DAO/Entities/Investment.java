package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;


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
        float amountInves;
        @Enumerated(EnumType.STRING)
        MethodInvestissement methodInvestissement;
        @ManyToOne
        User users;

}
