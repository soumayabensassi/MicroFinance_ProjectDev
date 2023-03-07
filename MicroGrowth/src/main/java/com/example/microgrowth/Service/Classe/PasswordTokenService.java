package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.RestPasswordToken;
import com.example.microgrowth.DAO.Repositories.RestPasswordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordTokenService {
    private final RestPasswordRepository passwordTokenRepository;


    public void saveConfirmationToken(RestPasswordToken token) {
        passwordTokenRepository.save(token);
    }

    public Optional<RestPasswordToken> getToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return passwordTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
    }

    public void deleteAllTokens(){
        passwordTokenRepository.deleteAll();
    }

}
