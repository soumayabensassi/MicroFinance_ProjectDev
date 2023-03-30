package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Repositories.BankAccountRepository;
import com.example.microgrowth.DAO.Repositories.ComplaintRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class KPIInsuranceRestController {

    private ComplaintRepository complaintRepository;


    private UserRepository userRepository;


    private BankAccountRepository bankAccountRepository;





    // Financial KPIs

    @GetMapping("/financial/CustomerKPI/{n}")
    public double financialKPI1(@PathVariable float n ) {
        // n: cost of sales and marketing
        float userCount = userRepository.countAllUsers();
        System.out.println(userCount);
        double Ncustomer = userCount ; // Number of Customers
        return n / Ncustomer;
    }

    @GetMapping("/financial/CashFlowKPI")
    public double financialKPI2(@RequestParam float j,@RequestParam float p) {

        return p-j;
    }

    @GetMapping("/financial/EPS-KPI")
    public double financialKPI3(@RequestParam int s, @RequestParam float l) {

        float income =bankAccountRepository.getAmountByRib("999999999") - l ; // Income inventory
        return income / s;
    }

    @GetMapping("/financial/ProfitMarginKPI")
    public double financialKPI4(@RequestParam float l) {

        float Revenue = bankAccountRepository.getAmountByRib("999999999");; //  revenue
        float Income = Revenue - l; //  income

        return Income / Revenue;
    }


    // Insurance Agency KPIs

    @GetMapping("/insurance/PRR-KPI")
    public double insuranceKPI5( @RequestParam int g , @RequestParam int h ) {
        // KPI: Policy Retention Rate
        // g : Policies Renewed
        // h : Total Policies
        return g / h;
    }

    @GetMapping("/insurance/Satisfaction-KPI")
    public float insuranceKPI6() {
        // Formula: Satisfaction Ratio= 1 - ( Non-Satisfied Customers / Total Customers )
         float userCount = userRepository.countAllUsers();
         float complaintCount = complaintRepository.countUniqueUsersWithComplaints();
        return 1 - (complaintCount / userCount)  ;
    }

}
