package com.example.microgrowth.DAO.Repositories;

import com.example.microgrowth.DAO.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PublicationRepository extends JpaRepository<Publication,Integer> {
    @Query(value = "select p.* from publication p where p.state=?1",nativeQuery = true)
    List<Publication> PublicationAprouve(boolean b);
}
