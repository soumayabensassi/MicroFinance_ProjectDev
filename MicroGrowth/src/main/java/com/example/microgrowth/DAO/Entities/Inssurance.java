package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Inssurance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idInsurance;
    float amount;
    @Temporal(TemporalType.DATE)
    static
    LocalDate startDate;
    @Temporal(TemporalType.DATE)
    static
    LocalDate endDate;
    public static long getDuration() {
        Duration duration = Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay());
        return duration.toDays();
    }

    @ManyToOne
    User users;
    @ManyToOne(cascade = CascadeType.ALL)
    ActivitySector activitySector;
}
