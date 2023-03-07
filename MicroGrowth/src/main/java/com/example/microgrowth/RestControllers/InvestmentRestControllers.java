package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
public class InvestmentRestControllers  {
    private IInvestment IInvestment;
    @GetMapping("/afficherInvestment")
    public List<Investment> afficher (){
        return IInvestment.selectAll();
    }
    @PostMapping("/ajouterInvestment")
    public Investment ajouter(@RequestBody Investment inv){

        return IInvestment.add(inv);
    }
    @DeleteMapping("/deleteInvestmentbyID/{id}")
    public void delete(@PathVariable int id)
    {
        IInvestment.deleteById(id);
    }
    @PutMapping("/modifierInvestment/{id}")
    public Investment modif(@RequestBody Investment inv){

        return IInvestment.modif(inv);
    }
    @PostMapping("/interet")
    public ResponseEntity<BigDecimal> calculerInteret(
            @RequestParam("methodInvestissement") MethodInvestissement method,
            @RequestParam("amountInves") BigDecimal amount,
            @RequestParam("duree") int duree) {

        BigDecimal interet = IInvestment.calculerInteret(method, amount, duree);

        return ResponseEntity.ok(interet);
    }
    @PostMapping("/calculerTauxInteret")
    public BigDecimal calculerTauxInteret(@RequestParam MethodInvestissement methodInvestissement,
                                          @RequestParam BigDecimal amountInves,
                                          @RequestParam int duree) {
        BigDecimal Tauxinteret = IInvestment.calculerTauxInteret(methodInvestissement, amountInves, duree);
        return Tauxinteret;
    }
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmerInvestissement(@RequestBody Investment inv,
                                                          @RequestParam String userEmail) {
        IInvestment.add(inv);
        IInvestment.sendNotificationEmail(userEmail);
        return ResponseEntity.ok("Investment confirmed.");
    }




}
