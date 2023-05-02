package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository  extends JpaRepository<Projet, Long> {
}
