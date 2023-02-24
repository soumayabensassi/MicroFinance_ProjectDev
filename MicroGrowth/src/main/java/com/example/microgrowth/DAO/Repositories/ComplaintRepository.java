package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint,Integer> {
}
