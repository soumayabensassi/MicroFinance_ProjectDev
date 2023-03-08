package com.example.microgrowth.Service.Classe;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.microgrowth.DAO.Entities.Investment;
import com.example.microgrowth.DAO.Entities.MethodInvestissement;
import com.example.microgrowth.DAO.Repositories.InvestmentRepository;
import com.example.microgrowth.Service.Interfaces.IInvestment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.*;

@Service


public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNotificationEmail(String userEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Notification d'investissement confirmé");
        msg.setText("Félicitations! Votre investissement a été bien confirmé");
        msg.setTo(userEmail);
        msg.setFrom("omezzinemariem@gmail.com");

        // Envoyer le message
        javaMailSender.send(msg);
        System.out.println("email sent succefully");
    }
}
