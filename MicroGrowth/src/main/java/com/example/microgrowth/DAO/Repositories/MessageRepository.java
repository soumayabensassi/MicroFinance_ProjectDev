package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Message;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findBySenderAndRecipient(User sender, User recipient);
    List<Message> findByRecipient(User recipient);

}
