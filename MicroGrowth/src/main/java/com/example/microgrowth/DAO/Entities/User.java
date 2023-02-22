package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idUser;

    String firstName;
    String lasttName;
    String password;
    String verifPassword;
    long phone;
    String email;
    String profession;
    long cin;

    @OneToMany(mappedBy = "users")
    List<Investment> investmentList;

    @OneToMany(mappedBy = "users")
    List<Credit> creditList;
    @OneToMany(mappedBy = "users")
    List<Inssurance> inssuranceList;
    @OneToMany(mappedBy = "users")
    List<Transaction> transactionList;
    @ManyToOne
    Role roles;
    @ManyToMany(mappedBy = "userList")
    List<Training> trainingList;
    @OneToMany(mappedBy = "users")
    List<Complaint> complaintList;
    @OneToMany(mappedBy = "users")
    List<Publication> publicationList;
}
