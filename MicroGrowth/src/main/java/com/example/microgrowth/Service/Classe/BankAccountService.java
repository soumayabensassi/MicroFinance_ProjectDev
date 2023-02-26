package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.BankAccount;
import com.example.microgrowth.DAO.Repositories.BankAccountRepository;
import com.example.microgrowth.Service.Interfaces.IBankAccount;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class BankAccountService implements IBankAccount {




        private BankAccountRepository bankAccountRepository;
        @Override
        public BankAccount add(BankAccount a) {
            return bankAccountRepository.save(a);
        }

        @Override
        public BankAccount edit(BankAccount a) {
            return bankAccountRepository.save(a);
        }

        @Override
        public List<BankAccount> selectAll() {
            return bankAccountRepository.findAll();
        }

        @Override
        public BankAccount SelectById(int id) {
            return bankAccountRepository.findById(id).get();
        }

        @Override
        public void deleteById(int id) {
            bankAccountRepository.deleteById(id);

        }
}
