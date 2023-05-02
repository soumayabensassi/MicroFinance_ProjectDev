package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.BankAccount;
import com.example.microgrowth.DAO.Entities.Transaction;
import com.example.microgrowth.DAO.Entities.Type_Transaction;
import com.example.microgrowth.DAO.Repositories.BankAccountRepository;
import com.example.microgrowth.DAO.Repositories.TransactionRepository;
import com.example.microgrowth.Service.Interfaces.ITransaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TransactionService implements ITransaction {

    private final TransactionRepository transactionRepository;
    private final BankAccountRepository bankAccountRepository;


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

    public List<Transaction> selectByRib(String Rib) {
        return transactionRepository.findAllByRibReceiverOrRibSource(Rib, Rib);
    }


    @Override
        public void makeDeposit(Transaction t) {
            BankAccount bankAccount = bankAccountRepository.findBankAccountByRib(t.getRibReceiver())
                    .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibReceiver()));
            if (t != null &&
                    t.getTypeTransaction().equals(Type_Transaction.DEPOSIT)
                    && bankAccount != null
            ) {
                bankAccount.setAmount(bankAccount.getAmount() + t.getAmountTransaction());
                if(t.getBankAccountList() ==null)
                    t.setBankAccountList(new ArrayList<>());
                 t.getBankAccountList().add(bankAccount);
                 transactionRepository.save(t);

            }
           else  throw new NotFoundException("Deposit not allowed");
        }

        @Override
        public void makeTransfer(Transaction t) {
            List<Transaction> toDayTransactions = transactionRepository
                    .findAllByDateTransactionAndRibSource(new Date(), t.getRibSource())
                    .orElseThrow(() -> new NotFoundException("not found"));
            BankAccount bankAccountReceiver = bankAccountRepository.findBankAccountByRib(t.getRibReceiver())
                    .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibReceiver()));
            BankAccount bankAccountSource= bankAccountRepository.findBankAccountByRib(t.getRibSource())
                    .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibSource()));
            float somme=0;
            for(Transaction transaction : toDayTransactions){
                if(transaction.getTypeTransaction().equals(Type_Transaction.BANK_TRANSFER))
                somme+=transaction.getAmountTransaction();
            }
            somme+=t.getAmountTransaction();
            if(t !=null
                    && somme < bankAccountSource.getMAX_WITHDRAWL_PER_DAY()
                    && t.getAmountTransaction()<= bankAccountSource.getAmount()+ bankAccountSource.getCOVER())
            {
                bankAccountSource.setAmount(bankAccountSource.getAmount()-t.getAmountTransaction());
                bankAccountReceiver.setAmount(bankAccountReceiver.getAmount()+t.getAmountTransaction());
                bankAccountSource.setCURRENT_BANKTRANSFER_PER_DAY((long) (bankAccountSource.getCURRENT_BANKTRANSFER_PER_DAY()-t.getAmountTransaction()));
                if(t.getBankAccountList() == null)
                    t.setBankAccountList(new ArrayList<>());
                t.getBankAccountList().add(bankAccountSource);
                t.getBankAccountList().add(bankAccountReceiver);
                transactionRepository.save(t);

            }
            else throw new NotFoundException("Transfer not allowed");

        }

        @Override
        public void makeWithdrawal(Transaction t) {
            List<Transaction> toDayTransactions = transactionRepository
                    .findAllByDateTransactionAndRibSource(new Date(), t.getRibSource())
                    .orElseThrow(() -> new NotFoundException("not found"));
            BankAccount bankAccount = bankAccountRepository.findBankAccountByRib(t.getRibReceiver())
                    .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibReceiver()));
            float somme=0;

if (t.getTypeTransaction().equals(Type_Transaction.WITHDRAWAL))    {        if(toDayTransactions != null){
            for(Transaction transaction : toDayTransactions){
                if(transaction.getTypeTransaction().equals(Type_Transaction.WITHDRAWAL))
                     somme+=transaction.getAmountTransaction();
            }}
            if(
                     somme < bankAccount.getCURRENT_WITHDRAWAL_PER_DAY() &&
                    t.getAmountTransaction()<= bankAccount.getAmount()+bankAccount.getCOVER()) {
                bankAccount.setAmount(bankAccount.getAmount() - t.getAmountTransaction());
                bankAccount.setCURRENT_WITHDRAWAL_PER_DAY((long) (bankAccount.getCURRENT_WITHDRAWAL_PER_DAY()-t.getAmountTransaction()));
                if(t.getBankAccountList() ==null)
                    t.setBankAccountList(new ArrayList<>());
                t.getBankAccountList().add(bankAccount);
                 transactionRepository.save(t);
            }}
           else throw new NotFoundException("withdrawal not allowed");
        }
        @Override
    public void makePayment(Transaction t) {
        List<Transaction> toDayTransactions = transactionRepository
                .findAllByDateTransactionAndRibSource(new Date(), t.getRibSource())
                .orElseThrow(() -> new NotFoundException("not found"));
        BankAccount bankAccountReceiver = bankAccountRepository.findBankAccountByRib(t.getRibReceiver())
                .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibReceiver()));
        BankAccount bankAccountSource= bankAccountRepository.findBankAccountByRib(t.getRibSource())
                .orElseThrow(() -> new NotFoundException("NO ACCOUNT WITH RIB :" + t.getRibSource()));
        float somme=0;
        for(Transaction transaction : toDayTransactions){
            if(transaction.getTypeTransaction().equals(Type_Transaction.CARD_PAYMENT))
                somme+=transaction.getAmountTransaction();
        }
        somme+=t.getAmountTransaction();
        if(t !=null
                && somme < bankAccountSource.getCURRENT_CARDPAYMENT_PER_DAY()
                && t.getAmountTransaction()<= bankAccountSource.getAmount()+ bankAccountSource.getCOVER())
        {
            bankAccountSource.setAmount(bankAccountSource.getAmount()-t.getAmountTransaction());
            bankAccountReceiver.setAmount(bankAccountReceiver.getAmount()+t.getAmountTransaction());
            bankAccountSource.setCURRENT_CARDPAYMENT_PER_DAY((long) (bankAccountSource.getCURRENT_CARDPAYMENT_PER_DAY()-t.getAmountTransaction()));
            if(t.getBankAccountList() == null)
                t.setBankAccountList(new ArrayList<>());
            t.getBankAccountList().add(bankAccountSource);
            t.getBankAccountList().add(bankAccountReceiver);
            transactionRepository.save(t);

        }
        else throw new NotFoundException("Transfer not allowed");

    }

}
