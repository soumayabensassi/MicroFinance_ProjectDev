package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.ActivitySector;
import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IInsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/api/inssurances")

public class InsuranceRestController {
    private IInsuranceService iInsuranceService;
    @GetMapping("/afficher-insurance")
    public List<Inssurance> afficher(){
        return iInsuranceService.selectAll();

    }
    @PostMapping("/ajouter-Insurance")
    public Inssurance ajouter(@RequestBody Inssurance insurance){
        return  iInsuranceService.add(insurance);

    }
    @PutMapping("/update-Insurance")
    public Inssurance update(@RequestBody Inssurance inssurance) {
        return iInsuranceService.edit(inssurance);
    }
    @GetMapping("/afficherAvecIdInsurance/{idInsurance}")

    public Inssurance afficherAvecIdInsurance(@PathVariable int idInsurance) {
        return iInsuranceService.selectById(idInsurance);
    }
    @DeleteMapping("/deleteInsurance/{idInsurance}")
    public void delete(@PathVariable int idInsurance)
    {

        iInsuranceService.deleteById(idInsurance);
    }


    private InsuranceRepository insuranceRepository;



    @PostMapping("/calculate-monthly-payment")
    public ResponseEntity<Double> calculateMonthlyPayment(@RequestParam Double loanAmount) {

        Double interestRate = 0.12;
        Double monthlyInterestRate = interestRate / 12;
        //Long loanTerm = (Inssurance.getDuration())/30;
        int loanTerm = 1;
        Double monthlyPaymentAmount = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -12 * loanTerm));

        // return the monthly payment amount
        return new ResponseEntity<>(monthlyPaymentAmount, HttpStatus.OK);
    }


    private ICredit iCredit;

    @PostMapping("/insurancerequestC")
    public String applyForInsuranceC(@RequestParam  int income, @RequestParam String mail, @RequestParam int Score) {



        List<Credit> lcredit=iCredit.SelectByEmail(mail);
        int S=0;
        for (Credit l : lcredit) {
            S+=l.getAmount();
        }
        System.out.println(S);
        double dti = calculateDTI(income, S);
        int creditScore = Score;

        if (dti > 0.43 || creditScore < 600) {
            return "Sorry, your insurance request has been refused.";
        } else {
            return "Congratulations, your insurance request has been accepted.";
        }
    }

    private double calculateDTI(int income, int debt) {
        return debt / income;
    }

    @PostMapping("/insurancerequest")
    public String applyForInsurance(@RequestParam  int income, @RequestParam int debt, @RequestParam int Score) {




        double dti = calculateDTI(income, debt);
        int creditScore = Score;

        if (dti > 0.43 || creditScore < 600) {
            return "Sorry, your insurance request has been refused.";
        } else {
            return "Congratulations, your insurance request has been accepted.";
        }
    }


}








