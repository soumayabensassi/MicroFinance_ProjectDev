package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
    int ancienneteEmploi;
    String typeContratEmploi ;
    int proprietaire;
    float salaire=800;
    Date dateNaissance=new Date();
    boolean historiqueCredit;
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Investment> investmentList;

    @OneToMany(mappedBy = "users")
            @JsonIgnore
    List<Credit> creditList;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Inssurance> inssuranceList;
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Transaction> transactionList;
//    @ManyToMany(fetch = FetchType.EAGER) // load the user and load their roles in the db
//    private Collection<Role> roles = new ArrayList<>();
    @ManyToOne
    Role roles;
    @ManyToMany(mappedBy = "userList")
    @JsonIgnore
    List<Training> trainingList;
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Complaint> complaintList;
    @OneToMany(mappedBy = "users")
    @JsonIgnore
    List<Publication> publicationList;
    boolean Active;
    @OneToOne
    @JsonIgnore
    ImageModel imageModel;

}
