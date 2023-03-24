package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Repositories.ComplaintRepository;
import com.example.microgrowth.Service.Interfaces.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class ComplaintService implements IComplaintService {
    private ComplaintRepository complaintRepository;
    @Override
    public Complaint add(Complaint complaint) {
        return complaintRepository.save(complaint) ;
    }

    @Override
    public Complaint edit(Complaint complaint) {
        return complaintRepository.save(complaint);
    }

    @Override
    public List<Complaint> selectAll() {
        return complaintRepository.findAll();
    }

    @Override
    public Complaint selectById(int idcomplaint) {
        return complaintRepository.findById(idcomplaint).orElse(null);
    }

    @Override
    public void deleteById(int idcomplaint) {
        complaintRepository.deleteById(idcomplaint);
    }

    @Override
    public void delete(Complaint complaint) {
        complaintRepository.delete(complaint);
    }

    @Override
    public List<Complaint> addAll(List<Complaint> listC) {
        return complaintRepository.saveAll(listC);
    }

    @Override
    public void deleteAll(List<Complaint> listC) {
        complaintRepository.deleteAll();
    }

    @Override
    public void deleteByState() {
        List<Complaint> listT = complaintRepository.selectByState1();
        complaintRepository.deleteAll(listT);
    }
}
