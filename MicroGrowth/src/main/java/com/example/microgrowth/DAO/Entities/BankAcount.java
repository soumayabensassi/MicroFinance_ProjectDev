package com.example.microgrowth.DAO.Entities;

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
    public class BankAcount implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int idBank;
        String rib;
        float Amount;
        @Temporal(TemporalType.DATE)
        Date dateAcount;
        @Enumerated(EnumType.STRING)
        Type_account typeAccount;
        @ManyToMany(mappedBy = "bankAcountList")
        List<Transaction> transactionList;


<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes
}
