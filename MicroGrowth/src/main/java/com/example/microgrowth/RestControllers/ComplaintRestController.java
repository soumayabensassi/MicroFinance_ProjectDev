package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.Service.Interfaces.IComplaintService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
@RestController
@AllArgsConstructor
@EnableScheduling
public class ComplaintRestController {
    private IComplaintService iComplaintService;
    @GetMapping("/afficherC")
    public List<Complaint> afficherC(){

        return iComplaintService.selectAll();
    }
    @PostMapping("/ajouterComplaint")
    public Complaint ajouterC(@RequestBody Complaint complaint){
        complaint.setDate(LocalDateTime.now());
        return iComplaintService.add(complaint);
    }
    @GetMapping("/afficherAvecIdC/{idcomplaint}")
    public Complaint afficherAvecIdC(@PathVariable int idcomplaint){
        return iComplaintService.selectById(idcomplaint);
    }
    @DeleteMapping("/deleteComplaint/{idcomplaint}")
    public void supprimerAvecIdC(@PathVariable int idcomplaint){
        iComplaintService.deleteById(idcomplaint);
    }

    @DeleteMapping("/deleteComplaint")
    public void supprimerC(Complaint complaint){
        iComplaintService.delete(complaint);
    }
    @PutMapping("/editComplaint")
    public Complaint modifier(@RequestBody Complaint complaint)
    {return iComplaintService.edit(complaint);
    }
    @Scheduled(cron = "0 0 0 * * *")
    @DeleteMapping("/deleteComplaintT")
    public void supprimerCT(){
        iComplaintService.deleteByState();
    }
}
