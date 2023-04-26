package com.example.microgrowth.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Publication implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPublication;
    @Column(nullable = false)
    String text;
    Boolean state = false;
    @ManyToOne
    User users;
    @OneToMany(mappedBy = "publications",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Comment> commentList;
    @OneToMany(mappedBy = "publications",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Likes> likesList;
    @OneToMany(mappedBy = "publications",cascade = CascadeType.ALL)
    @JsonIgnore
    List<Dislike> dislikeList;
    int nombreLike;
    int nombreDislike;

}
