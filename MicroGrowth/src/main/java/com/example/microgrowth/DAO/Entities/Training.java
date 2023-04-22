package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Training implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTraining;
    //@NotNull(message = "Ce champ  ne peut pas être vide")
    @Column(nullable = false)
    String title;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    // @NotNull(message = "Le champ nom ne peut pas être vide")
    Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    Date finishdate;

    boolean state;
    @Column(nullable = false)
    float price;
    @Column(nullable = false)
    int nbrOfPlace;
    @Column(nullable = false)
    String subject;
    @ManyToMany
    List<User> userList;
    @OneToMany(mappedBy = "trainings")
    List<Participer> participerList;
    @OneToMany(mappedBy = "trainings")
    List<NonParticiper> nonparticiperList;
    @OneToMany(mappedBy = "trainings")
    List<Rating> rating;

}
