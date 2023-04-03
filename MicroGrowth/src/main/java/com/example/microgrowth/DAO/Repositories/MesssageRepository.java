package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesssageRepository extends JpaRepository<Message,Long> {
}
