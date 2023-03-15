package com.example.microgrowth.DAO.Entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Training implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idTraining;
    @Temporal(TemporalType.DATE)
    Date startDate;
    @Temporal(TemporalType.DATE)
    Date finishdate;
    boolean state;
    float price;
    int nbrOfPlace;
    String subject;
    @Transient
     double rating = 0.0;

    public int getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(int idTraining) {
        this.idTraining = idTraining;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishdate() {
        return finishdate;
    }

    public void setFinishdate(Date finishdate) {
        this.finishdate = finishdate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNbrOfPlace() {
        return nbrOfPlace;
    }

    public void setNbrOfPlace(int nbrOfPlace) {
        this.nbrOfPlace = nbrOfPlace;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @ManyToMany
    List<User> userList;
    @OneToMany(mappedBy = "trainings")
    List<Participer> participerList;
    @OneToMany(mappedBy = "trainings")
    List<NonParticiper> nonparticiperList;

}
