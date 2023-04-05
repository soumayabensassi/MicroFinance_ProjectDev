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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/api/insurances")

public class InsuranceRestController {
    private IInsuranceService iInsuranceService;

    @GetMapping("/admin/afficher-insurance")
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

    @PutMapping("/admin/update-Insurance")
    public Inssurance update(@RequestBody Inssurance inssurance) {
        return iInsuranceService.edit(inssurance);
    }

    @GetMapping("/admin/afficherAvecIdInsurance/{idInsurance}")

    public Inssurance afficherAvecIdInsurance(@PathVariable int idInsurance) {
        return iInsuranceService.selectById(idInsurance);
    }

    @DeleteMapping("/admin/deleteInsurance/{idInsurance}")
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

    @PostMapping("/admin/insurancerequestC")
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
    { LocalDate date_now =  LocalDate.now();
        System.out.println(date_now);

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date=date_now.format(formatter);
        System.out.println(iInsuranceService.getUserEmail(date));
        for (String e:iInsuranceService.getUserEmail(date))
             {
                 System.out.println(e);
<<<<<<< Updated upstream
            emailReceiptInsurance.sendHtmlEmail(e,"Insurance",emailReceiptInsurance.EmailReceipt("Hello User",10,"This is your",date));
=======
            emailReceiptInsurance.sendHtmlEmail(e,"Insurance",emailReceiptInsurance.EmailReceipt("User",10,"This is your",date_now));
>>>>>>> Stashed changes
        }
    }


    @PostMapping("/calculateTotalAmount")
    public float calculateTotalAmount ( @RequestParam int id , @RequestParam float interestRate){
        return iInsuranceService.selectById(id).getAmount()*(1+interestRate);
    }

}








