package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikeRepository extends JpaRepository <Dislike,Integer> {
    Dislike findByUsersEmailAndPublicationsIdPublication(String email,int i);
    Dislike findByUsersEmailAndCommentIdComment(String email,int i);
    @Query(value = "SELECT COUNT(l.id_dislike)FROM dislike l WHERE l.publications_id_publication=?1",nativeQuery = true)
    int totalDislLike(int id);
    @Query(value = "SELECT COUNT(l.id_dislike)FROM dislike l WHERE l.comment_id_comment=?1",nativeQuery = true)
    int totalDislLikeComment(int id);
}
