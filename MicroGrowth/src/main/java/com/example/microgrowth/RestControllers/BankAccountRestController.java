package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.BankAccount;
import com.example.microgrowth.Service.Interfaces.IBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor

public class BankAccountRestController {
public IBankAccount iBankAccount;


    @GetMapping("/admin/showBankAccount")
    public List<BankAccount> afficher()
    {
        return iBankAccount.selectAll();
    }

    @PostMapping("/admin/addBankAccount")

    public BankAccount ajouter(@RequestBody BankAccount bankAccount)
    {
        return iBankAccount.add(bankAccount);
    }

    @PutMapping("/admin/updateBankAccount")
    public BankAccount update(@RequestBody BankAccount bankAccount)
    {return iBankAccount.edit(bankAccount);}

    @GetMapping("/admin/showBankAccount/{id}")
    public BankAccount AfficherByID(@PathVariable int id)
    {
        return iBankAccount.SelectById(id);
    }

    @DeleteMapping("/admin/deleteBankAccount/{id}")
    public void delete(@PathVariable int id)
    {
        iBankAccount.deleteById(id);
    }


}

