package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Classe.BonDeCommandeService;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Classe.UserService;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class InvestmentRestControllers  {
    @Autowired
    private EmailService EmailService;
    @Autowired
    private BonDeCommandeService bonDeCommandeService;
    @Autowired
    private UserService userService;
    private IInvestment IInvestment;
    @Autowired
    private IUser Iuser;

    @GetMapping("/admin/afficherInvestment")
    public List<Investment> afficher (){
        return IInvestment.selectAll();
    }

    @PostMapping("/admin/ajouterInvestment")
    public Investment ajouter(@RequestBody Investment inv) throws MessagingException{
        EmailService.sendNotificationEmail("omezzinemariem@gmail.com");

        return IInvestment.add(inv);
    }

    @DeleteMapping("/admin/deleteInvestmentbyID/{id}")
    public void delete(@PathVariable int id)
    {
        IInvestment.deleteById(id);
    }

    @PutMapping("/admin/modifierInvestment/{id}")
    public Investment modif(@RequestBody Investment inv){

        return IInvestment.modif(inv);
    }

    @PostMapping("/admin/interet")
    public double calculerInteret(
            @RequestParam MethodInvestissement methodInvestissement,
            @RequestParam double amountInves,
            @RequestParam int duree) {

        double interet = IInvestment.calculerInteret(methodInvestissement, amountInves, duree);

        return interet;
    }

    @PostMapping("/admin/calculerTauxInteret")
    public double calculerTauxInteret1(@RequestParam MethodInvestissement methodInvestissement,
                                       @RequestParam double amountInves,
                                       @RequestParam int duree) {
        double Tauxinteret = IInvestment.calculerTauxInteret(methodInvestissement, amountInves, duree);
        return Tauxinteret;
    }

    @GetMapping("/{investmentId}/bon-de-commande")
    public ResponseEntity<byte[]> generateBonDeCommande(@PathVariable int investmentId) {

        Investment investment = IInvestment.selectById(investmentId);
        User user = Iuser.SelectById(investment.getUsers().getIdUser());
        byte[] pdfBytes = bonDeCommandeService.genererBonDeCommande(user, investment);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("bon_de_commande.pdf")
                .build());
        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }
    @GetMapping("admin/RevenuIInvesstisement")
    public Double getRevenusInvesstisement()
    {
        double resultat=0;

        List<Investment> investmentList =IInvestment.getInvestmentByAnnee();
        for (Investment c : investmentList)
        {
            resultat+=IInvestment.Revenu_INVISTISSEMENT(c);
        }

        return  resultat;
    }

}
