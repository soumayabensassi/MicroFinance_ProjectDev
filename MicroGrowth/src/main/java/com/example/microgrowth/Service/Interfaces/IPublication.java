package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Entities.User;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface IPublication {
    Publication add(Publication a);
    Publication edit(Publication a);
    List<Publication> selectAll();
    Publication SelectById(int id);
    void deleteById(int id);
    List<Publication> SelectPublicationAprouve();
}
