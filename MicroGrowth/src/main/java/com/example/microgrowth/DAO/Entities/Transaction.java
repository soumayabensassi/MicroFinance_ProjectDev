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
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTransaction;
    @Enumerated(EnumType.STRING)
    Type_Transaction typeTransaction;
    @Enumerated(EnumType.STRING)
    CategorieTransaction categorieTransaction;
    @Temporal(TemporalType.DATE)
    Date dateTransaction;
    float amountTransaction;
    String ribSource;
    String ribReceiver;
    @ManyToOne
    User users;
    @ManyToMany
            @JsonIgnore
    List<BankAccount> bankAccountList;



}
