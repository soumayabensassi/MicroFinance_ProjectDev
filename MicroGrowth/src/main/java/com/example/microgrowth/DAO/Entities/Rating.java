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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"trainings_id_training", "users_id_user"})
})
public class Rating implements Serializable {
    @Id
     int id;
     int score;
    @ManyToOne
     Training trainings;
    @ManyToOne
     User users;
    public Rating(User userByEmail, Training selectById){
        this.users=userByEmail;
        this.trainings=selectById;
    }

    public Rating(User userByEmail, Training selectById, int idrating) {
        this.users=userByEmail;
        this.trainings=selectById;
        this.id=id;
    }
}
