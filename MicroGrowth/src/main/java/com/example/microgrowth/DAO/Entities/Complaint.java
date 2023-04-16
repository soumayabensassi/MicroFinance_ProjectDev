package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Complaint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idComplaint;
    //@Temporal(TemporalType.DATE)
    //@NotNull(message = "Le champ nom ne peut pas être vide")
    Date date;
    //@NotNull(message = "Le champ nom ne peut pas être vide")
    String subject;
   // @NotNull(message = "Le champ nom ne peut pas être vide")
    String text;
    //@NotNull(message = "Le champ nom ne peut pas être vide")
    boolean state;
    @ManyToOne
    User users;
    RetourComplaint retourComplaint;

}
