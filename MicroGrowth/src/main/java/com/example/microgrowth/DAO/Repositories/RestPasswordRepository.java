package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.RestPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RestPasswordRepository extends JpaRepository<RestPasswordToken, Long> {

    Optional<RestPasswordToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE RestPasswordToken c " + "SET c.confirmedAt = ?2 " + "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}