package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Repositories.PublicationRepository;
import com.example.microgrowth.Service.Interfaces.IPublication;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PublicationService implements IPublication {

    private PublicationRepository publicationRepositor;
    @Override
    public Publication add(Publication a) {
        return publicationRepositor.save(a);
    }

    @Override
    public Publication edit(Publication a) {
        return publicationRepositor.save(a);
    }

    @Override
    public List<Publication> selectAll() {
        return publicationRepositor.findAll();
    }

    @Override
    public Publication SelectById(int id) {
        return publicationRepositor.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        publicationRepositor.deleteById(id);
    }

    @Override
    public List<Publication> SelectPublicationAprouve() {
        return publicationRepositor.PublicationAprouve(true);
    }
}
