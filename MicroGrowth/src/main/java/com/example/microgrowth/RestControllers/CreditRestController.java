package com.example.microgrowth.RestControllers;


import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Penalite;
import com.example.microgrowth.DAO.Entities.Publication;

import com.example.microgrowth.DAO.Entities.*;

import com.example.microgrowth.DAO.Repositories.ActivitySectorRepository;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Classe.EmailService;
import com.example.microgrowth.Service.Interfaces.ICredit;

import com.example.microgrowth.Service.Interfaces.IInvestment;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import com.lowagie.text.DocumentException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@CrossOrigin("http://localhost:4200")
@RestController
@AllArgsConstructor
public class CreditRestController {
    private ICredit iCredit;
    CreditRepository creditRepository;

    IInvestment iInvestment;
    IUser iUser;
    ActivitySectorRepository activitySectorRepository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/admin/afficherCredits")
    public List<Credit> afficherCredits()
    {
        return iCredit.selectAll();
    }
    @PostMapping("/user/ajouterCreditByuser/{email}")
    public String ajouterCredit_user(@RequestBody Credit credit,@PathVariable String email)
    {   credit.setUsers(iUser.getUserByEmail(email));
        iCredit.add_credit_user(credit);

        System.out.println("test");

        System.out.println(credit.getIdCredit());
        int s=iCredit.scoreCredit(credit.getIdCredit(),email);
        System.out.println(s);
        credit.setScore_credit(s);
        return iCredit.add_credit_user(credit);
    }
    @PostMapping("/admin/ajouterCreditByadmin/{email}")
    public Credit ajouterCredit_admin(@RequestBody Credit credit,@PathVariable String email)
    { credit.setUsers(iUser.getUserByEmail(email));

        credit.setActiviteSecteurs(activitySectorRepository.findById(1).get());
        return iCredit.add_credit_admin(credit);
    }
    @PutMapping("/admin/updateCredit")
    public Credit updateCredit(@RequestBody Credit credit)
    {return iCredit.edit(credit);}
    @GetMapping("/admin/afficherCreditbyID/{id}")
    public Credit AfficherCreditByID(@PathVariable int id)
    {
        return iCredit.SelectById(id);
    }
    @GetMapping("/user/afficherCreditbyID/{id}")

    public Credit AfficherCreditByIDuser(@PathVariable int id)
    {
        return iCredit.SelectById(id);
    }
    @DeleteMapping("/admin/deleteCreditAdmin/{id}")
    public void delete(@PathVariable int id)
    {
        iCredit.deleteById(id);
    }
    @DeleteMapping("/user/deleteCreditUser/{id}")
    public void deleteCreditUser(@PathVariable int id)
    {
        iCredit.deleteById(id);
    }

    @GetMapping("/admin/AfficherScoreCredit/{id}")
    public int afficherScore(@PathVariable int id)
    {
        String email="aziz.cherif1@esprit.tn";
        return iCredit.scoreCredit(id,email);
    }
   // @GetMapping("/afficherScoreCredit/{idUser}/{idCredit}/{idCompte}")
    //public int afficherscore(@PathVariable int idUser, @PathVariable int idCredit,@PathVariable int idCompte){
//return iCredit.afficherScore()    }

    @GetMapping("/user/afficherTableauCredit/{id}")
    public float[][] calcul_tableau_credit(@PathVariable int id){
       return iCredit.calcul_tableau_credit(creditRepository.findById(id).get());
    }

    @GetMapping("/admin/CalculRentabilité")
    public double calcul_Rentabilté_desCredits()
    {   //Rentabilité financière = (résultat d'exploitation — impôts sur les bénéfices —
        // versés aux dettes financières) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCredit(c);
        }
        //En Tunisie, la réglementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital réel nécessaire dépendra des besoins spécifiques de la micro banque.
        double Rentabilte=(resultat-(resultat*.25))/2000000;
        System.out.println("resultat ="+resultat);
        System.out.println("Rentabilte ="+Rentabilte);
        return  Rentabilte;
    }
    @GetMapping("/admin/CalculRentabilitéNonActualisé")
    public double calcul_Rentabilté_desCreditsNonActualisé()
    {   //Rentabilité financière = (résultat d'exploitation — impôts sur les bénéfices(25% des Résultat)  —
        // versés aux dettes financières) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCreditNonActialise(c);
        }
        //En Tunisie, la réglementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital réel nécessaire dépendra des besoins spécifiques de la micro banque.
        double Rentabilte=(resultat-(resultat*.25))/2000000;
        System.out.println("resultat ="+resultat);
        System.out.println("Rentabilte ="+Rentabilte);
        return  Rentabilte;
    }
    @GetMapping("/admin/CalculNetInterestMarge")
    public double calculNet_Interest_Marge() {

        return iCredit.calcul_Net_Interest_Marge();
    }

    @GetMapping("/admin/TauxDeRemboursement")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public double calculTauxDeRemboursement() {
        System.out.println(iCredit.TauxRemboursement());
        return iCredit.TauxRemboursement();
    }
    @GetMapping("/admin/TauxDeDefaut")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public double calculTauxDeDefaut() {

        return iCredit.TauxDeDefaut();
    }
   @GetMapping("/admin/ReturnOnEquity")
   @Scheduled(cron = "0 0 10 31 12 ?")
   public  double CalculROE()
   {
       double resultatNet=iCredit.CalculResultatNET();
       System.out.println("Resultat net :" +resultatNet);
       return  resultatNet/2000000;

   }
    @GetMapping("/admin/ReturnOnAssets")
    @Scheduled(cron = "0 0 10 31 12 ?")
    public  double CalculROA() {
        double resultatNet = iCredit.CalculResultatNET();
        double ActifNet = iCredit.CalculActifCredit() + iCredit.CalculActifRéserve();
        return resultatNet / ActifNet;
    }
    @GetMapping("/afficherDiffDate/{id}")
    public Credit afficherDifDate(@PathVariable int id)
    {
        iCredit.ajouter_date_30(creditRepository.findById(id).orElse(null));
        return null;
    }
    @GetMapping("/admin/revenuCredit")
    public double revenuCredit()
    {
       return iCredit.CalculActifCredit();
    }
    @GetMapping("/creditremboursee")
    public List<Credit> afficherCreditRem(){
        return creditRepository.selectCreditRembourseeParMois();
    }
        @PostMapping("/admin/accordePenalite")
    public  void Accorde_penalite(){
        iCredit.Accorde_penalite();
    }
    @GetMapping("/admin/export-to-pdf-credits")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException
    {
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=credit" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);
        List < Credit > listofCredits = creditRepository.findAll();
        PdfGeneratorCredit generator = new PdfGeneratorCredit();

        generator.generate(listofCredits, response);
}
EmailService emailService;
    @GetMapping("/ProposerCredit/{id}")
    void SendEmailPenalite(@PathVariable int id) {
        User user = userRepository.findById(id).get();
        System.out.println(user.getEmail());
        emailService.sendCalcukCredit(user);
    }
    @GetMapping("/user/SimulateurCredit/{montant}/{nbmois}")
    double[] SimulateurCredit(@PathVariable float montant,@PathVariable int nbmois){
        return iCredit.SimulateurCredit(montant,nbmois);
    }
    @PostMapping("/user/envoyerProposition/{nbmois}")
    public ResponseEntity<String> PropCredit(@PathVariable int nbmois){
        try {

            iCredit.genererCreditPDF(nbmois);
            iCredit.envoyerCreditParEmail();
        } catch (IOException | javax.mail.MessagingException | DocumentException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("Added successfully.");
    }
@GetMapping("/MaxCredit/{nbmois}")
    public double MaxCredit(@PathVariable int nbmois){
        return iCredit.MaxCredit(nbmois);
}
@PostMapping("/admin/refuserCredit/{id}")
    void RefuserCredit(@PathVariable int id){

        iCredit.RefuserCreditAuUser(creditRepository.findById(id).get());
}
    @PostMapping("/admin/AccepterCredit/{id}")
    void AccepterCredit(@PathVariable int id){

        iCredit.AffecterCreditAuUser(creditRepository.findById(id).get());
    }
    @GetMapping("/admin/afficherPacksback")
    public List<Credit> afficherPacks(){
        return creditRepository.afficherCreditPackBack();
    }
    @GetMapping("/afficherPacks")
    public List<Credit> afficherPacksFront(){
        return creditRepository.CreditsPack();
    }
    @GetMapping("/admin/afficherCreditPerso")
    public List<Credit> afficherCreditPerso(){
        return creditRepository.afficherCreditPerso();
    }
    @PostMapping("/user/demanderPack/{idcredit}/{email}")
    void accorderPack(@PathVariable int idcredit, @PathVariable String email){
        Credit credit=creditRepository.findById(idcredit).get();
        credit.setUsers(iUser.getUserByEmail(email));
        iCredit.accorderPack(idcredit);
    }
    @GetMapping("/afficherMesCredit/{email}")
    public List<Credit> afficherMesCredit(@PathVariable String email){
        return creditRepository.afficherMesCredits(email);
    }
    @GetMapping("/admin/afficherDemandePack")
    public List<Credit> afficherCreditsPackDemande(){
        return creditRepository.afficherCreditsPackDemande();
    }
}
