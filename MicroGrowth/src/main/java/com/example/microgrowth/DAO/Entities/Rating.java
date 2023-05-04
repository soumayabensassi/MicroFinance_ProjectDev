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
/*@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"trainings_id_training", "users_id_user"})
})*/
public class Rating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idRating;
     int score;
    @ManyToOne
            @JsonIgnore
     Training trainings;
    @ManyToOne
            @JsonIgnore
     User users;
    /*public Rating(User userByEmail, Training selectById){
        this.users=userByEmail;
        this.trainings=selectById;
    }*/

    /*public Rating(User userByEmail, Training selectById, int idrating) {
        this.users=userByEmail;
        this.trainings=selectById;
        this.id=id;
    }*/
}
