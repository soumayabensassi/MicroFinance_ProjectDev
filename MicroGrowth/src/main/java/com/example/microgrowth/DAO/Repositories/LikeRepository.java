package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Integer> {
    Likes findByUsersEmailAndPublicationsIdPublication(String email,int i);
    Likes findByUsersEmailAndCommentIdComment(String email,int i);
    @Query(value = "SELECT COUNT(l.id_like)FROM likes l WHERE l.publications_id_publication=?1",nativeQuery = true)
    int totalLiike(int id);
    @Query(value = "SELECT COUNT(l.id_like)FROM likes l WHERE l.comment_id_comment=?1",nativeQuery = true)
    int totalLiikeComment(int id);
}
