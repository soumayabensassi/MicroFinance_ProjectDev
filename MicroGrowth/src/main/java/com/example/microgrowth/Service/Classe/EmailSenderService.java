package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Complaint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import com.example.microgrowth.Service.Interfaces.EmailSender;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
@AllArgsConstructor
public class EmailSenderService implements EmailSender {
        private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderService.class);
        @Autowired
        private final JavaMailSender mailSender;

        @Override
        @Async
        public void send(String to, String email) {
            try {
                MimeMessage mimeMessage = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
                helper.setText(email, true);
                helper.setTo(to);
                helper.setSubject("MicroGrowth");
                helper.setFrom("soumaya.bensasi@gmail.com");
                mailSender.send(mimeMessage);
            } catch (MessagingException e) {
                LOGGER.error("failed to send email", e);
                throw new IllegalStateException("failed to send email");
            }
        }



    public void sendEmail(String toEmail,String subject,String body,String attachment)throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
        message.setFrom("myriambrahmi23@gmail.com");
        message.setTo(toEmail);
        message.setText((body));
        message.setSubject(subject);

        FileSystemResource fileSystemResource= new FileSystemResource(new File(attachment));
        message.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
        mailSender.send(mimeMessage);
        System.out.println("Mail Sent Successfully...");
    }

    public void sendEmail1(String toEmail,String subject)throws MessagingException {
        MimeMessage mimeMessage=mailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
        message.setFrom("myriambrahmi23@gmail.com");
        message.setTo(toEmail);
        //message.setText((body));
        message.setSubject(subject);
//        Complaint complaint=new Complaint();
//        String htmlBody = "<html><head><style>.button {display: inline-block; padding: 10px 20px; font-size: 18px; font-weight: bold; text-decoration: none; color: #FFFFFF; background-color: #4CAF50; border-radius: 5px; border: none;}</style></head><body><p>Bonjour,</p><p>Etes-vous satisfait du traitement? :</p><a href='http://localhost:8082/MicroGrowth/" + complaint.getIdComplaint() + "/satitfait' class='button'>OUI</a></body><head><style>.button {display: inline-block; padding: 10px 20px; font-size: 18px; font-weight: bold; text-decoration: none; color: #FFFFFF; background-color: #4CAF50; border-radius: 5px; border: none;}</style></head><a href='https://monsite.com/ajouter' class='button'>NON</a><p>Merci,</p><p>L'équipe de Spring</p></body></html>";
//
//        message.setText(htmlBody, true);

        //mailSender.send(mimeMessage);
        System.out.println("Mail Sent Successfully...");
    }
}
