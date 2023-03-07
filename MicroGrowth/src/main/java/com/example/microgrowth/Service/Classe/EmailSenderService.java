package com.example.microgrowth.Service.Classe;

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

@Service
public class EmailSenderService {


    @Autowired
    private JavaMailSender mailSender;
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
}
