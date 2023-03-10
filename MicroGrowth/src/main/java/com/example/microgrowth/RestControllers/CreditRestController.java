package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Repositories.CreditRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class CreditRestController {
    private ICredit iCredit;
    CreditRepository creditRepository;
    IInvestment iInvestment;
    @GetMapping("/afficherCredits")
    public List<Credit> afficherCredits()
    {
        return iCredit.selectAll();
    }
@PostMapping("/ajouterCreditByuser")
    public Credit ajouterCredit_user(@RequestBody Credit credit)
    {
        return iCredit.add_credit_user(credit);
    }
    @PostMapping("/ajouterCreditByadmin")
    public Credit ajouterCredit_admin(@RequestBody Credit credit)
    {
        return iCredit.add_credit_admin(credit);
    }
    @PutMapping("/updateCredit")
    public Credit updateCredit(@RequestBody Credit credit)
    {return iCredit.edit(credit);}
    @GetMapping("/afficherCreditbyID/{id}")
    public Credit AfficherCreditByID(@PathVariable int id)
    {
        return iCredit.SelectById(id);
    }
    @DeleteMapping("/deleteCredit/{id}")
    public void delete(@PathVariable int id)
    {
        iCredit.deleteById(id);
    }

    @GetMapping("/AfficherScoreCredit/{id}")
    public int afficherScore(@PathVariable int id)
    {
        return iCredit.scoreCredit(id);
    }
   // @GetMapping("/afficherScoreCredit/{idUser}/{idCredit}/{idCompte}")
    //public int afficherscore(@PathVariable int idUser, @PathVariable int idCredit,@PathVariable int idCompte){
//return iCredit.afficherScore()    }

    @GetMapping("/afficherTableauCredit/{id}")
    public void calcul_tableau_credit(@PathVariable int id){
        iCredit.calcul_tableau_credit(creditRepository.findById(id).orElse(null));
    }

    @GetMapping("/admin/CalculRentabilit??")
    public double calcul_Rentabilt??_desCredits()
    {   //Rentabilit?? financi??re = (r??sultat d'exploitation ??? imp??ts sur les b??n??fices ???
        // vers??s aux dettes financi??res) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCredit(c);
        }
        //En Tunisie, la r??glementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital r??el n??cessaire d??pendra des besoins sp??cifiques de la micro banque.
        double Rentabilte=(resultat-(resultat*.25))/2000000;
        System.out.println("resultat ="+resultat);
        System.out.println("Rentabilte ="+Rentabilte);
        return  Rentabilte;
    }
    @GetMapping("/admin/CalculRentabilit??NonActualis??")
    public double calcul_Rentabilt??_desCreditsNonActualis??()
    {   //Rentabilit?? financi??re = (r??sultat d'exploitation ??? imp??ts sur les b??n??fices(25% des R??sultat)  ???
        // vers??s aux dettes financi??res) / capitaux propres

        double resultat=0;
        List<Credit> creditsList =iCredit.selectAll();
        for (Credit c : creditsList)
        {
            resultat+=iCredit.calcul_Rentabilite_parCreditNonActialise(c);
        }
        //En Tunisie, la r??glementation bancaire exige que le capital minimum d'une micro banque soit de 2 millions de dinars tunisiens
        // (environ 700 000 euros) pour obtenir une licence bancaire.
        // Cependant, le capital r??el n??cessaire d??pendra des besoins sp??cifiques de la micro banque.
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
    public double calculTauxDeRemboursement() {

        return iCredit.TauxRemboursement();
    }
    @GetMapping("/admin/TauxDeDefaut")
    public double calculTauxDeDefaut() {

        return iCredit.TauxDeDefaut();
    }
   @GetMapping("/admin/ReturnOnEquity")
    public  double CalculROE()
   {
       double resultatNet=iCredit.CalculResultatNET();
       return  resultatNet/2000000;

   }
    @GetMapping("/admin/ReturnOnAssets")
    public  double CalculROA()
    {
        double resultatNet=iCredit.CalculResultatNET();
        double ActifNet=iCredit.CalculActifCredit()+iCredit.CalculActifR??serve();
        return  resultatNet/ActifNet;

    }
}
