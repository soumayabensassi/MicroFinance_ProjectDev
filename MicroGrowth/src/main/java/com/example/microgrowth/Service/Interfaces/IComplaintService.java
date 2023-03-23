package com.example.microgrowth.Service.Interfaces;

import com.example.microgrowth.DAO.Entities.Complaint;

import java.util.*;

public interface IComplaintService {
    Complaint add(Complaint complaint);
    Complaint edit(Complaint complaint);
    List<Complaint>selectAll();
    Complaint selectById(int id);
    void deleteById(int id);
    void delete(Complaint complaint);
    List<Complaint> addAll(List<Complaint> listC);
    void deleteAll(List<Complaint> listC);
    void deleteByState();


}
