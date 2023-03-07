package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Dislike;
import com.example.microgrowth.DAO.Entities.Likes;
import com.example.microgrowth.DAO.Repositories.DislikeRepository;
import com.example.microgrowth.Service.Interfaces.IDislike;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DislikeService implements IDislike {
    private DislikeRepository dislikeRepository;

    @Override
    public Dislike add(Dislike a) {
        a.setNbr(1);
        return dislikeRepository.save(a);
    }

    @Override
    public void deleteById(int id) {
dislikeRepository.deleteById(id);
    }

    @Override
    public Dislike verifDislike(String email,int i) {
        return dislikeRepository.findByUsersEmailAndPublicationsIdPublication(email,i);    }
}
