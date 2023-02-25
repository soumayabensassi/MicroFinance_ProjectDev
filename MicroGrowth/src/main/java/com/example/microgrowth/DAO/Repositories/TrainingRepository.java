package com.example.microgrowth.DAO.Repositories;


import com.example.microgrowth.DAO.Entities.Training;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingRepository extends JpaRepository<Training,Integer> {
}
