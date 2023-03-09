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
public class Desinteresse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idDesinteresse;
    int nbrnonparticip;
    @ManyToOne
    User users;
    @ManyToOne
    Training trainings;
    public Desinteresse(User userByEmail, Training selectById){
        this.users=userByEmail;
        this.trainings=selectById;
    }
}
