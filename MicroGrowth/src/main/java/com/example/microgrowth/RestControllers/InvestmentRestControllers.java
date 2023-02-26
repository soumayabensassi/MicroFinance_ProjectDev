package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class InvestmentRestControllers  {
    private IInvestment IInvestment;
    @GetMapping("/afficher")
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

}
