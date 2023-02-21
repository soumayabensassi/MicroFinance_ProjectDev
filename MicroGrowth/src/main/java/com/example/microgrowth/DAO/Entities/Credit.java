package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Credit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idCredit;
    @Enumerated(EnumType.STRING)
    Type_Credit typeCredit;
    float amount;
    @Temporal(TemporalType.DATE)
    Date demandDate;
    @Temporal(TemporalType.DATE)
    Date obtainingDate;
    float intrestRaiting;
    float monthlyAmount;
    boolean state;
    @ManyToOne

    User users;

    @ManyToOne
    ActivitySector activiteSecteurs;
     @OneToOne (cascade = CascadeType.ALL)
     Intreview intreviews;
    @OneToOne (cascade = CascadeType.ALL)
    Inssurance inssurances;

}
