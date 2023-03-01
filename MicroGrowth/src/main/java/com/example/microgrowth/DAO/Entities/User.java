package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
    @Column(unique = true)
    String email;
    String profession;
    @Column(unique = true)
    long cin;

    @OneToMany(mappedBy = "users")
    List<Investment> investmentList;

    @OneToMany(mappedBy = "users")
    List<Credit> creditList;
    @OneToMany(mappedBy = "users")
    List<Inssurance> inssuranceList;
    @OneToMany(mappedBy = "users")
    List<Transaction> transactionList;
    @ManyToMany(fetch = FetchType.EAGER) // load the user and load their roles in the db
    private Collection<Role> roles = new ArrayList<>();
    @ManyToMany(mappedBy = "userList")
    List<Training> trainingList;
    @OneToMany(mappedBy = "users")
    List<Complaint> complaintList;
    @OneToMany(mappedBy = "users")
    List<Publication> publicationList;

    public User(String sbs, String sbs1, String s, String s1, long i, String s2, String etude, long i1) {
        this.firstName=sbs;
        this.lasttName=sbs;
        this.password=s;
        this.verifPassword=s1;
        this.phone=i;
        this.email=s2;
        this.profession=etude;
        this.cin=i1;

    }
}
