package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Interesse;
import com.example.microgrowth.DAO.Entities.Rating;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Repositories.RatingRepository;
import com.example.microgrowth.Service.Interfaces.IRating;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingService implements IRating {
    private RatingRepository ratingRepository;

    @Override
    public Rating add(Rating r) {


        return ratingRepository.save(r);
    }

    @Override
    public void deleteById(int r) {
        ratingRepository.deleteById(r);

    }

    @Override
    public Rating verifRating(String email, int r) {
        return ratingRepository.findByUsersEmailAndTrainingsIdTraining(email, r);

    }
}
