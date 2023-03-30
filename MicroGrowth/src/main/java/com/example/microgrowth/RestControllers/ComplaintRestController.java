package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.ComplaintRepository;
import com.example.microgrowth.Service.Classe.ComplaintService;
import com.example.microgrowth.Service.Classe.EmailSenderService;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Interfaces.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.List;
@RestController
@AllArgsConstructor
@EnableScheduling
public class ComplaintRestController {
    private EmailService senderService;
    private IComplaintService iComplaintService;
    private ComplaintService complaintService;
    private  ComplaintRepository complaintRepository;
    @GetMapping("/admin/afficherC")
    public List<Complaint> afficherC(){

        return iComplaintService.selectAll();
    }
    @PostMapping("/user/ajouterComplaint")
    public Complaint ajouterC(@RequestBody Complaint complaint){
        Complaint c =new Complaint();
        complaint.setDate(LocalDateTime.now());
       // for(User us : complaintRepository.selectUsers()) {
            senderService.sendNotificationEmailComplaint(c.getUsers().getEmail());
       // }
        c.setState(false);
        return iComplaintService.add(complaint);
    }
    @GetMapping("/both/afficherAvecIdC/{idcomplaint}")
    public Complaint afficherAvecIdC(@PathVariable int idcomplaint){
        return iComplaintService.selectById(idcomplaint);
    }
    @DeleteMapping("/both/deleteComplaint/{idcomplaint}")
    public void supprimerAvecIdC(@PathVariable int idcomplaint){
        iComplaintService.deleteById(idcomplaint);
    }

    @DeleteMapping("/admin/deleteComplaint")
    public void supprimerC(Complaint complaint){
        iComplaintService.delete(complaint);
    }
    @PutMapping("/user/editComplaint")
    public Complaint modifier(@RequestBody Complaint complaint)
    {return iComplaintService.edit(complaint);
    }
    @Scheduled(cron = "0 0 0 * * *")
    @DeleteMapping("/admin/deleteComplaintT")
    public void supprimerCT(){
        iComplaintService.deleteByState();
    }
    @RequestMapping ("/{id}/traiter")
    public ResponseEntity<Void> traiterReclamation(@PathVariable int id) {
        complaintService.traiterReclamation(id);
        return ResponseEntity.ok().build();
    }
    @RequestMapping("/admin/test/{id}")
    public void envoyer(@PathVariable int id){
        complaintService.traiterReclamation1( id);
    }
}
