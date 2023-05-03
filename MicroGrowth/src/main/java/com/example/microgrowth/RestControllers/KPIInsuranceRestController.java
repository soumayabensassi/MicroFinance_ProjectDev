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

    @GetMapping("/admin/financial/CustomerKPI/{n}")
    public double financialKPI1(@PathVariable float n ) {
        // n: cost of sales and marketing
        float userCount = userRepository.countAllUsers();
        System.out.println(userCount);
        double Ncustomer = userCount ; // Number of Customers
        return n / Ncustomer;
    }

    @GetMapping("/admin/financial/CashFlowKPI")
    public double financialKPI2(@RequestParam float j,@RequestParam float p) {

        return p-j;
    }

    @GetMapping("/admin/financial/EPS-KPI")
    public double financialKPI3(@RequestParam int s, @RequestParam float l) {

        float income =(bankAccountRepository.getAmountByRib("123")) - l ; // Income inventory
        return income / s;
    }

    @GetMapping("/admin/financial/ProfitMarginKPI")
    public double financialKPI4(@RequestParam float l) {

        float Revenue = bankAccountRepository.getAmountByRib("123"); //  revenue
        float Income = Revenue - l; //  income

        return Income / Revenue;
    }


    // Insurance Agency KPIs

    @GetMapping("/admin/insurance/PRR-KPI")
    public double insuranceKPI5( @RequestParam float g , @RequestParam float h ) {
        // KPI: Policy Retention Rate
        // g : Policies Renewed
        // h : Total Policies
        return g / h;
    }

    @GetMapping("/admin/insurance/Satisfaction-KPI")
    public float insuranceKPI6() {
        // Formula: Satisfaction Ratio= 1 - ( Non-Satisfied Customers / Total Customers )
         float userCount = userRepository.countAllUsers();
         float complaintCount = complaintRepository.countUniqueUsersWithComplaints();
        return 1 - (complaintCount / userCount)  ;
    }

}
