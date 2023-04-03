package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.RetourComplaint;
import com.example.microgrowth.DAO.Entities.Training;
import com.example.microgrowth.DAO.Repositories.ComplaintRepository;
import com.example.microgrowth.Service.Interfaces.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    private JavaMailSender mailSender;


    //private ComplaintRepository complaintRepository;

    public void traiterReclamation(int id) {
        Complaint reclamation = complaintRepository.findByIdComplaint(id);

        reclamation.setState(true);
        complaintRepository.save(reclamation);
        // Envoyer un e-mail de confirmation
        //String recipient = reclamation.getUsers().getEmail();
        //String subject = "Confirmation de traitement de votre réclamation";
//        String message = "Bonjour " + reclamation.getUsers().getFirstName() + ",\n\nVotre réclamation a été traitée avec succès.\n\nCliquez sur le lien suivant pour marquer votre réclamation comme traitée :\n\nhttp://localhost:8082/MicroGrowth/" + reclamation.getIdComplaint() + "/traiter\n\nCordialement,\nL'équipe de support technique";
//
//        sendEmail(recipient, subject, message);
    }

    private void sendEmail(String recipient, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
    public void traiterReclamation1(int id) {
        Complaint reclamation = complaintRepository.findByIdComplaint(id);


        String recipient = reclamation.getUsers().getEmail();
        String subject = "Confirmation de traitement de votre réclamation";
        String message = "Bonjour " + reclamation.getUsers().getFirstName() + ",\n\nVotre réclamation a été traitée avec succès.\n\nCliquez sur le lien suivant pour marquer votre réclamation comme traitée :\n\nhttps://localhost:8082/MicroGrowth/" + reclamation.getIdComplaint() + "/traiter\n\nCordialement,\nL'équipe de support technique";

        sendEmail(recipient, subject, message);
    }
    public void satisfait(int id) {
        Complaint reclamation = complaintRepository.findByIdComplaint(id);

        reclamation.setRetourComplaint(RetourComplaint.SATISFAIT);
        complaintRepository.save(reclamation);

    }
    public void nonsatisfait(int id) {
        Complaint reclamation = complaintRepository.findByIdComplaint(id);

        reclamation.setRetourComplaint(RetourComplaint.NON_SATISFAIT);
        complaintRepository.save(reclamation);

    }
}
