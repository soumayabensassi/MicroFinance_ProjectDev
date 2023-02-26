package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
}
