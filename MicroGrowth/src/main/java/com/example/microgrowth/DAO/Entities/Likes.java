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
public class Likes implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idLike;
    int nbr;
    @ManyToOne
    User users;
    @ManyToOne
    Publication publications;
    @ManyToOne
    Comment comment;
    public Likes(User userByEmail, Publication selectById) {
        this.users=userByEmail;

        this.publications=selectById;

    }
    public Likes(User userByEmail, Comment selectById) {
        this.users=userByEmail;

        this.comment=selectById;

    }
}
