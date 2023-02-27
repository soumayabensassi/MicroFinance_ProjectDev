package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Transaction;
import com.example.microgrowth.DAO.Repositories.TransactionRepository;
import com.example.microgrowth.Service.Interfaces.ITransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService implements ITransaction {
    private TransactionRepository transactionRepository;
    @Override
    public Transaction add(Transaction a) {
        return transactionRepository.save(a);
    }

    @Override
    public Transaction edit(Transaction a) {
        return transactionRepository.save(a);
    }

    @Override
    public List<Transaction> selectAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction SelectById(int id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {
        transactionRepository.deleteById(id);

    }
}
