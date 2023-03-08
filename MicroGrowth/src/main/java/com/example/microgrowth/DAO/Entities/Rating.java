package com.example.microgrowth.DAO.Entities;

import javax.persistence.*;

@Entity
public class Rating {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    int nbrR;
    @ManyToOne
    User users;
    @ManyToOne
    Training trainings;
    public Rating(User userByEmail, Training selectById) {
        this.users=userByEmail;

        this.trainings=selectById;

    }
    public Rating() {}
}
