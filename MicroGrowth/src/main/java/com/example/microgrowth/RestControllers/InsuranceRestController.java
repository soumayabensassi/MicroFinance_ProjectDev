package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.Service.Interfaces.IInsuranceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@AllArgsConstructor

public class InsuranceRestController {
    private IInsuranceService iInsuranceService;
    @GetMapping("/afficherinsurance")
    public List<Inssurance> afficher(){
        return iInsuranceService.selectAll();

    }
    @PostMapping("/ajouterAbonnement")
    public Inssurance ajouter(@RequestBody Inssurance insurance){
        return  iInsuranceService.add(insurance);

    }
    @GetMapping("/afficherAvecIdInsurance/{id}")

    public Inssurance afficherAvecIdInsurance(@PathVariable int idInsurance) {
        return iInsuranceService.selectById(idInsurance);
    }
}
