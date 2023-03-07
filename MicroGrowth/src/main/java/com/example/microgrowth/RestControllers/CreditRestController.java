package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Credit;
import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.Service.Interfaces.ICredit;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreditRestController {
    private ICredit iCredit;
    @GetMapping("/afficherCredits")
    public List<Credit> afficherCredits()
    {
        return iCredit.selectAll();
    }
@PostMapping("/ajouterCreditByUser")
    public Credit ajouterCredit(@RequestBody Credit credit)
    {
        return iCredit.add_credit_user(credit);
    }
    @PostMapping("/ajouterCreditByAdmin")
    public Credit ajouterCreditAdmin(@RequestBody Credit credit)
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
}
