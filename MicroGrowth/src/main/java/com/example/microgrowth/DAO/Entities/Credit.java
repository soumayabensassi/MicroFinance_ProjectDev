package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(nullable = false)
    float amount_credit;
    float amount_garant;
    int duree;
    @Temporal(TemporalType.DATE)
    Date demandDate;
    @Temporal(TemporalType.DATE)
    Date obtainingDate;
    float intrestRaiting;
    @Column(nullable = false)
    float monthlyAmount;
    int state;
    boolean pack;
    boolean garanties;
    @ManyToOne
 @JsonIgnore
    User users;

    @ManyToOne
    ActivitySector activiteSecteurs;
     @OneToOne (cascade = CascadeType.ALL)
     Intreview intreviews;
    @OneToOne (cascade = CascadeType.ALL)
    Inssurance inssurances;

}
