package com.example.microgrowth.RestControllers;
import java.text.*;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.InvestmentRepository;
import com.example.microgrowth.Service.Classe.BonDeCommandeService;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Classe.UserService;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.util.Date;






@RestController
@AllArgsConstructor
public class InvestmentRestControllers  {
    @Autowired
    private EmailService EmailService;

   private BonDeCommandeService bonDeCommande;
    InvestmentRepository investmentRepository;
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

   @GetMapping("/user/export/pdfinvestissement")
    public void exportToPDF(HttpServletResponse response, @RequestParam int id) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=investments_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

       List <Investment> listInvestments =  investmentRepository.getlistByidUser(id);

        BonDeCommandeService bonDeCommandeService = new BonDeCommandeService(listInvestments);
        bonDeCommandeService.export(response);
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

    @GetMapping("/user/ponzii/{iduser}")
    public Double calculerTauxPonzii(@PathVariable int iduser) {
        double taux = IInvestment.calculerTauxPonzii(iduser);
        return taux ;
    }
    @RestController
    public class SessionController {

        @GetMapping("/session/{n}/{x}")
        public double getSession(@PathVariable double n, @PathVariable double x) {
            return IInvestment.session(n, x);
        }
    }


}
