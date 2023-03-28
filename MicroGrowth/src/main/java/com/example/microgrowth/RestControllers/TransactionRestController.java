package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Publication;
import com.example.microgrowth.DAO.Entities.Transaction;
import com.example.microgrowth.DAO.Util.TransactionPDFExporter;
import com.example.microgrowth.Service.Classe.TransactionService;
import com.example.microgrowth.Service.Interfaces.ITransaction;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.lowagie.text.DocumentException;
@RestController
@AllArgsConstructor
public class TransactionRestController {
    private ITransaction iTransaction;
    @Autowired
    private TransactionService service;

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response,@RequestParam String Rib) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Transaction> listTransactions = service.selectByRib(Rib);

        TransactionPDFExporter exporter = new TransactionPDFExporter(listTransactions);
        exporter.export(response);

    }

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
