package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    String title;
    @Temporal(TemporalType.DATE)
    Date startDate;
    @Temporal(TemporalType.DATE)
    Date finishdate;
    boolean state;
    float price;
    int nbrOfPlace;
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
