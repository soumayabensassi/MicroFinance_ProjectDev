package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;

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
    public class BankAccount implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int idBank;
        String rib;
        float Amount;
        float COVER=100;
    long CURRENT_WITHDRAWAL_PER_DAY=300;
    long CURRENT_CARDPAYMENT_PER_DAY=300;
    long CURRENT_BANKTRANSFER_PER_DAY=300;
    long MAX_WITHDRAWL_PER_DAY = 300;
    long MAX_CARDPAYMENT_PER_DAY = 300;
    long MAX_BANKTRANSFER_PER_DAY=300;


    int MAX_OCC_PER_DAY = 3;

    @Temporal(TemporalType.DATE)
        Date dateAcount;
        @Enumerated(EnumType.STRING)
        Type_account typeAccount;
        @ManyToMany(mappedBy = "bankAccountList")
        List<Transaction> transactionList;

        @OneToOne
        User user ;
}
