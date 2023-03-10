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
public class Dislike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idDislike;
    int nbr;
    @ManyToOne
    User users;
    @ManyToOne
    Publication publications;
    @ManyToOne
    Comment comment;

    public Dislike(User userByEmail, Publication selectById) {
        this.users=userByEmail;

        this.publications=selectById;

    }
    public Dislike(User userByEmail, Comment selectById) {
        this.users=userByEmail;

        this.comment=selectById;

    }
}
