package com.example.microgrowth.DAO.Repositories;

<<<<<<< Updated upstream
public interface CreditRepository {
=======
import com.example.microgrowth.DAO.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit,Integer> {
>>>>>>> Stashed changes
}
