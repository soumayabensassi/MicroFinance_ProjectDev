package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.ActivitySectorRepository;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.Service.Classe.EmailReceiptInsurance;
import com.example.microgrowth.Service.Interfaces.ICredit;
import com.example.microgrowth.Service.Interfaces.IInsuranceService;
import com.example.microgrowth.Service.Interfaces.IUser;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/api/insurances")

public class InsuranceRestController {
    private IInsuranceService iInsuranceService;

    @GetMapping("/afficher-insurance")
    public List<Inssurance> afficher() {
        return iInsuranceService.selectAll();

    }

    @GetMapping("/afficher-amount/{idInsurance}")
    public float afficheramountAvecIdInsurance(@PathVariable int idInsurance) {
        return iInsuranceService.selectById(idInsurance).getAmount();
    }

    @PostMapping("/ajouter-Insurance")
    public Inssurance ajouter(@RequestBody Inssurance insurance) {
        return iInsuranceService.add(insurance);

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
    public void delete(@PathVariable int idInsurance) {

        iInsuranceService.deleteById(idInsurance);
    }


    private InsuranceRepository insuranceRepository;
    private ActivitySectorRepository activitySectorRepository;


    @PostMapping("/calculate-monthly-payment")
    public double calculateMonthlyPayment(@RequestParam float loanAmount, @RequestParam double interestRate) {

        double monthlyInterestRate = interestRate / 12;
        //Long loanTerm = (Insurance.getDuration())/30;
        int loanTerm = 1;

        // return the monthly payment amount
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -12 * loanTerm));
    }


    @PostMapping("/calculate-monthly-paymentS")
    public double calculateMonthlyPaymentS(@RequestParam int idS, @RequestParam float TotalInsuredValue, @RequestParam float Deductible) {

        double interestRate = activitySectorRepository.getInterestRateByIdSecteur(idS);
        double monthlyInterestRate = interestRate / 12;
        //Long loanTerm = (Insurance.getDuration())/30;
        int loanTerm = 1;
        float CorrelationRatio = activitySectorRepository.getCorrelationRatioByIdSecteur(idS);


        // return the monthly payment amount
        return (CorrelationRatio * (TotalInsuredValue - Deductible) * monthlyInterestRate) / 12;
    }


    private ICredit iCredit;

    @PostMapping("/insurancerequestC")
    public String applyForInsuranceC(@RequestParam int income, @RequestParam String mail, @RequestParam int Score) {


        List<Credit> lcredit = iCredit.SelectByEmail(mail);
        int S = 0;
        for (Credit l : lcredit) {
            S += l.getAmount_credit();
        }
        System.out.println(S);
        double dti = calculateDTI(income, S);
        int creditScore = Score;

        if (dti > 0.43 || creditScore < 85) {
            return "Sorry, your insurance request has been refused.";
        } else {
            return "Congratulations, your insurance request has been accepted.";
        }
    }

    private double calculateDTI(int income, int debt) {
        return debt / income;
    }

    @PostMapping("/insurancerequest-KPI")
    public String applyForInsurance(@RequestParam int income, @RequestParam int debt) {


        double dti = calculateDTI(income, debt);

        if (dti > 0.43) {
            return "Sorry, your insurance request has been refused.";
        } else {
            iInsuranceService.add(Inssurance.builder().build());
            return "Congratulations, your insurance request has been accepted.";
        }
    }
    private IUser iUser;
    private EmailReceiptInsurance emailReceiptInsurance;
    @Scheduled(fixedRate = 20000)
    //@Scheduled(cron = "0 0 0 * * ?")
    public void SendReceiptEmail() throws MessagingException
    { Date date_now = new Date();
        System.out.println(date_now);
        System.out.println(iInsuranceService.getUserEmail(date_now));
        for (String e:iInsuranceService.getUserEmail(date_now))
             {
                 System.out.println(e);
            emailReceiptInsurance.sendHtmlEmail(e,"Insurance",emailReceiptInsurance.EmailReceipt("Hello User",10,"This is your",date_now));
        }
    }

    @PostMapping
    public String handleInsuranceInterface(@RequestBody String message) {
        // handle incoming message and redirect to appropriate URL
        if (message.equals("insurance")) {
            return "redirect:/api/insurances";
        } else {
            return "redirect:/home";
        }
    }

}








