package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Entities.Transaction;
import com.example.microgrowth.Service.Interfaces.ITransaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TransactionRestController {
    private ITransaction iTransaction;

    @GetMapping("/showTransaction")
    public List<Transaction> afficher()
    {
        return iTransaction.selectAll();
    }

    @PostMapping("/addTransaction")

    public Transaction ajouter(@RequestBody Transaction transaction)
    {
        return iTransaction.add(transaction);
    }

    @PutMapping("/updateTransaction")
    public Transaction update(@RequestBody Transaction transaction)
    {return iTransaction.edit(transaction);}

    @GetMapping("/showTransactionByID/{id}")
    public Transaction AfficherByID(@PathVariable int id)
    {
        return iTransaction.SelectById(id);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public void delete(@PathVariable int id)
    {
        iTransaction.deleteById(id);
    }



    @PostMapping("/makeDeposit")
    public ResponseEntity<String> makeDeposit(@RequestBody Transaction t){
        iTransaction.makeDeposit(t);
        return ResponseEntity.ok().body("Deposit Successful");

    }
    @PostMapping("/makeWithdrawal")
    public ResponseEntity<String> makeWithdrawal(@RequestBody Transaction t){
        iTransaction.makeWithdrawal(t);
            return ResponseEntity.ok().body("Withdrawal Successful");

    }

    @PostMapping("makeTransfer")
    public ResponseEntity<String> makeTransfer(@RequestBody Transaction t){
        iTransaction.makeTransfer(t);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Transfer Successful");
    }




}
