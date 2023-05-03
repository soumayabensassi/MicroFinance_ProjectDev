package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.BankAccount;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.BankAccountRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.IBankAccount;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BankAccountService implements IBankAccount {


    private BankAccountRepository bankAccountRepository;

    @Autowired
    UserRepository userRepository;

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

    @Override
    @Scheduled(cron = "0 0 0 * * * ")
    public void resetLimit() {
        for (BankAccount bankAccount : bankAccountRepository.findAll()) {
            bankAccount.setCURRENT_BANKTRANSFER_PER_DAY(bankAccount.getMAX_BANKTRANSFER_PER_DAY());
            bankAccount.setCURRENT_CARDPAYMENT_PER_DAY(bankAccount.getMAX_CARDPAYMENT_PER_DAY());
            bankAccount.setCURRENT_WITHDRAWAL_PER_DAY(bankAccount.getMAX_CARDPAYMENT_PER_DAY());

        }
    }

    @Override
    public BankAccount SelectByIdUser(int id) {
       return this.bankAccountRepository.findBankAccountByUser_IdUser(id);
    }

    @Override
    @Scheduled(cron = "0 0 1 * * *")
    public void notifUpdate() {
        for (BankAccount bankAccount : bankAccountRepository.findAll()) {
            Optional<User> user = Optional.of(userRepository.findById(bankAccount.getIdBank()).get());

            Twilio.init("ACbefcebcd919893a1dd413e7a6c06d7fd", "5c8080a85e3b4eb905967b4d5b0a2e59");

            Message.creator(new PhoneNumber(String.valueOf(user.get().getPhone())),
                    new PhoneNumber("+15075843922"), "Dear"+user.get().getFirstName()+", /r/n"+"your balance has been updated, please check your account statement.").create();




        }

    }
}