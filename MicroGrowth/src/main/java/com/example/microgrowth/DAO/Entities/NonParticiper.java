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
public class NonParticiper implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idNonParticiper;
    int nbrNP;
    @ManyToOne
    User users;
    @ManyToOne
    Training trainings;
    public NonParticiper(User userByEmail, Training selectById) {
        this.users=userByEmail;

        this.trainings=selectById;

    }
}
