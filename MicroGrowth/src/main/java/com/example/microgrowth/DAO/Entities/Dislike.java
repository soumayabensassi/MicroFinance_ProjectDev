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
}
