package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.RestPasswordToken;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.DAO.Repositories.RestPasswordRepository;
import com.example.microgrowth.DAO.Repositories.UserRepository;
import com.example.microgrowth.Service.Interfaces.EmailSender;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailService {
    private final UserService appUserService;
    private final EmailValidator emailValidator;
//    private final ConfirmationTokenService confirmationTokenService;
    private final PasswordTokenService passwordTokenService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailSender emailSender;

    @Autowired
    UserRepository userRepo;

//    @Autowired
    RestPasswordRepository passwordTokenRepository;
public String forgetpassword(String email) {

    boolean isValidEmail = emailValidator.test(email);
    String token ="";

    if (!isValidEmail) {
        throw new IllegalStateException("email not valid");

    }


    token = UUID.randomUUID().toString();
    User u = userRepo.findByEmail(email);//.orElse(null);
    RestPasswordToken resetToken = new RestPasswordToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            u

    );
    passwordTokenRepository.save(resetToken);

    String link = "http://localhost:4200/updatepPassword/"+token+"/"+email;
    emailSender.send(email, buildEmailReset(u.getFirstName()+' '+ u.getLasttName(),link));
    return token;
}
    public void ConfirmeCompte(String email) {

        boolean isValidEmail = emailValidator.test(email);

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");

        }

        User u = userRepo.findByEmail(email);//.orElse(null);
        String link = "http://localhost:4200/confirmeCompte/"+email;
        emailSender.send(email, BuildConfirmeCompte(u.getFirstName()+' '+ u.getLasttName(),link));
    }

    @Transactional
    public int resetPassword(String token,String email,String password) {
        RestPasswordToken resetPasswordToken = passwordTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new IllegalStateException("token not found"));

        if (resetPasswordToken .getConfirmedAt() != null) {
            return -1;
        }

        LocalDateTime expiredAt = resetPasswordToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return 0;
        }

        resetPasswordToken.setConfirmedAt(LocalDateTime.now());
        passwordTokenRepository.save(resetPasswordToken);

        String encodedPassword = bCryptPasswordEncoder
                .encode(password);
        userRepo.resetPassword(encodedPassword, email);
        return 1;
    }

    private String buildEmailReset(String name, String link) {
    //return "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for calling us. Please click on the below link to reset your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> " + link + " <br> <p> Activate Now </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>";
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for calling us. Please click on the below link to reset your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    



    }

    private String BuildConfirmeCompte(String name, String link) {
        //return "<p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for calling us. Please click on the below link to reset your password: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> " + link + " <br> <p> Activate Now </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>";
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Active Your Account</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for trusting us. Please click on the below link to active your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n See you soon" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";




    }
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendNotificationEmail(String userEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Notification d'investissement confirmé");
        msg.setText("Félicitations! Votre investissement a été bien confirmé");
        msg.setTo(userEmail);
        msg.setFrom("mariem.omezzine@esprit.tn");

        // Envoyer le message
        javaMailSender.send(msg);
        System.out.println("email sent succefully");
    }


    public void sendPenaliteEmail(String userEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Avertissement pour dépassement de pénalités");
        msg.setText("Cher Mr,\n" +
                "\n" +
                "Nous vous écrivons pour vous informer que vous avez dépassé les trois pénalités autorisées pour le mois en cours. Comme vous le savez, notre entreprise a une politique stricte en matière de retards de paiement afin d'assurer une relation commerciale saine et équitable avec tous nos clients.\n" +
                "\n" +
                "Nous vous rappelons que chaque pénalité entraîne des frais supplémentaires qui s'accumulent avec le temps et peuvent avoir un impact négatif sur votre cote de crédit. Nous vous encourageons donc à régler rapidement vos paiements afin d'éviter de nouveaux frais.\n" +
                "\n" +
                "Nous sommes conscients que des retards de paiement peuvent arriver à tout le monde, et nous sommes toujours prêts à trouver des solutions pour vous aider à respecter vos obligations financières. Si vous avez des difficultés pour payer vos factures, nous vous invitons à nous contacter immédiatement pour discuter des options de paiement possibles.\n" +
                "\n" +
                "Nous espérons que cette situation sera résolue rapidement, et nous vous remercions de votre compréhension.\n" +
                "\n" +
                "Cordialement,\n" +
                "\n" +
                "MicroGrowth");
        msg.setTo(userEmail);
        msg.setFrom("microfinance.pidev@gmail.com");

        // Envoyer le message
        javaMailSender.send(msg);
        System.out.println("email sent succefully");
    }

    public void sendCalcukCredit(User user) {
        float salaire = user.getSalaire();
        if (salaire < 2000 && salaire > 800) {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setSubject("Montant Credit");

            double montant = ((((salaire * 0.43)) * 12) / 1.2) * 5;
            String m = Double.toString(montant);
            msg.setText("You can have a loan of " + m);
            // msg.setText("taux : 20%");
            msg.setTo(user.getEmail());
            msg.setFrom("myriambrahmi23@gmail.com");

            // Envoyer le message
            javaMailSender.send(msg);
            System.out.println("email sent succefully");
        }


    }
    public void sendNotificationEmailComplaint(String userEmail) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setSubject("Réception de réclamation confirmé");
        msg.setText("Bien reçu, votre récalmation a été bien enregistrée. Je vous prie de patienter. Votre récalamation sera traitée dans les 24h ");
        msg.setTo(userEmail);
        msg.setFrom("mariem.omezzine@esprit.tn");

        // Envoyer le message
        javaMailSender.send(msg);
        System.out.println("email sent succefully");
    }
}