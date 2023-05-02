package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idComment;
    @Column(nullable = false)
    String text;
    @ManyToOne
    User users;
    @ManyToOne
    Publication publications;
    int nombreLikeComment;
    int nombreDislikeComment;
}
