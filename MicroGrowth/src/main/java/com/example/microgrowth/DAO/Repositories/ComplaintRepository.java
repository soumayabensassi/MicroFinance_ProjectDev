package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {

    @Query("SELECT COUNT(DISTINCT c.users.idUser) FROM Complaint c")
    Long countUniqueUsersWithComplaints();
}
