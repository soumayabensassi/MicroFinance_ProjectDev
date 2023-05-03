package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Complaint;
import com.example.microgrowth.DAO.Entities.RetourComplaint;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.ComplaintRepository;
import com.example.microgrowth.Service.Classe.ComplaintService;
import com.example.microgrowth.Service.Classe.EmailSenderService;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Interfaces.IComplaintService;
import com.example.microgrowth.Service.Interfaces.IMicroGrowth;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@RestController
@AllArgsConstructor
@EnableScheduling
public class ComplaintRestController {
    private EmailService senderService;
    private IComplaintService iComplaintService;
    private ComplaintService complaintService;
    IMicroGrowth iMicroGrowth;
    private  ComplaintRepository complaintRepository;
    @GetMapping("/afficherC")
    public List<Complaint> afficherC(){

        return iComplaintService.selectAll();
    }
    @PostMapping("/user/ajouterComplaint")
    public Complaint ajouterC(@RequestBody Complaint complaint){
        Date d=new Date();
        complaint.setDate(d);
       // for(User us : complaintRepository.selectUsers()) {
            senderService.sendNotificationEmailComplaint(iMicroGrowth.getCurrentUserName());
       // }
        complaint.setState(false);
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
    public ResponseEntity<?> traiterReclamation(@PathVariable int id) {
        complaintService.traiterReclamation(id);
        complaintService.selectById(id);
        return ResponseEntity.ok().body("reclamation validé");
    }
    @RequestMapping("/envoyerMailReclamation/{id}")
    public void envoyer(@PathVariable int id) throws MessagingException {
        complaintService.traiterReclamation1( id);
    }
EmailSenderService emailSenderService;
    private final JavaMailSender mailSender;
    @RequestMapping("/AvisTraitement")
    public void AvisTraitement() throws MessagingException {
        List<Complaint> reclamation = complaintRepository.selectByState1();
        for(Complaint rec : reclamation){
        String subject = "Confirmation de traitement de votre réclamation";
        String recipient = rec.getUsers().getEmail();
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
            message.setFrom("myriambrahmi23@gmail.com");
            message.setTo(recipient);
            //message.setText((body));
            message.setSubject(subject);
            //Complaint complaint=new Complaint();
            String htmlBody = "<html><head><style>.button {display: inline-block; padding: 10px 20px; font-size: 18px; font-weight: bold; text-decoration: none; color: #FFFFFF; background-color: #4CAF50; border-radius: 5px; border: none;}</style></head><body><p>Bonjour,</p><p>Etes-vous satisfait du traitement? :</p><a href=\"https://localhost:8082/MicroGrowth/" + rec.getIdComplaint() + "/satisfait\" class='button'>OUI</a></body><head><style>.button {display: inline-block; padding: 10px 20px; font-size: 18px; font-weight: bold; text-decoration: none; color: #FFFFFF; background-color: #4CAF50; border-radius: 5px; border: none;}</style></head><a href=\"https://localhost:8082/MicroGrowth/" + rec.getIdComplaint() + "/nonsatisfait\" class='button'>NON</a><p>Merci,</p><p>L'équipe de Spring</p></body></html>";

            message.setText(htmlBody, true);

            mailSender.send(mimeMessage);
        emailSenderService.sendEmail1( recipient, subject);}
    }
    @RequestMapping("/{id}/satisfait")
    public void satisfait(@PathVariable int id) {
        complaintService.satisfait( id);
    }
    @RequestMapping("/{id}/nonsatisfait")
    public void nonsatisfait(@PathVariable int id) {
        complaintService.nonsatisfait( id);
    }
    @RequestMapping("/stat")
    public String statistique(){
         Double satisfait=(complaintRepository.calculsatisfait(RetourComplaint.SATISFAIT)/complaintRepository.totale())*100;
        Double nonsatisfait=(complaintRepository.calculsatisfait(RetourComplaint.NON_SATISFAIT)/complaintRepository.totale())*100;
        String satis=Double.toString(satisfait);
        String nonsatis=Double.toString(nonsatisfait);
        return "Le pourcentage des users satisfaits :"+satis+"%\n Le pourcentage des users non satisfaits"+nonsatis+"%";
    }
}
