package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.LikeRepository;
import com.example.microgrowth.Service.Interfaces.ILike;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LikeService implements ILike {
   private LikeRepository likeRepository;
    @Override
    public Likes add(Likes a) {
        a.setNbr(1);
        return likeRepository.save(a);
    }

    @Override
    public void deleteById(int id) {
        likeRepository.deleteById(id);

    }

    @Override
    public Likes verifLike(String email,int i) {
        return likeRepository.findByUsersEmailAndPublicationsIdPublication(email,i);
//       if (likes !=null)
//        return false;
//
//        return true;
    }
}
