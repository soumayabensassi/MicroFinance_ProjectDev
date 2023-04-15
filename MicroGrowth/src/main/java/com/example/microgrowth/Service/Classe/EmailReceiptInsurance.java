package com.example.microgrowth.Service.Classe;

import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
@AllArgsConstructor

public class EmailReceiptInsurance {
    private JavaMailSender mailSender;


    public void sendHtmlEmail(String toEmail, String subject, String htmlMessage) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlMessage, true);

        mailSender.send(message);
    }

    public String EmailReceipt (String name , float amount , String description , String purchase_date) {
        String supportUrl = "MicroGrowthUrl" ;

        return  "<span class=\"preheader\">This is a receipt for your recent purchase on " + purchase_date + ". No payment is due with this receipt.</span>\n" +
                "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\">\n" +
                "      <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "        <tr>\n" +
                "          <td class=\"email-masthead\">\n" +
                "            <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                "             MicroGrowth \n" +
                "          </a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <!-- Email Body -->\n" +
                "        <tr>\n" +
                "          <td class=\"email-body\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "              <!-- Body content -->\n" +
                "              <tr>\n" +
                "                <td class=\"content-cell\">\n" +
                "                  <div class=\"f-fallback\">\n" +
                "                    <h1>Hi " + name + ",</h1>\n" +
                "                    <p>Thanks for choosing [MicroGrowth]. This email is the receipt for your insurance payment. No payment is due.</p>\n" +
                "                    <p>This payment will appear as “Deposit” on your credit card statement.</p>\n" +
                "                    <table class=\"purchase\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "                      \n" +
                "                      <tr>\n" +
                "                        <td width=\"80%\" class=\"purchase_item\"><span class=\"f-fallback\">" + description + "</span></td>\n" +
                "                        <td class=\"align-right\" width=\"20%\" class=\"purchase_item\"><span class=\"f-fallback\">" + amount + "</span></td>\n" +
                "                      </tr>\n" +
                "                      \n" +
                "                      <tr>\n" +
                "                        <td width=\"80%\" class=\"purchase_footer\" valign=\"middle\">\n" +
                "                          <p class=\"f-fallback purchase_total purchase_total--label\">Total</p>\n" +
                "                        </td>\n" +
                "                        <td width=\"20%\" class=\"purchase_footer\" valign=\"middle\">\n" +
                "                          <p class=\"f-fallback purchase_total\">" + amount + "</p>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <p>If you have any questions about this receipt, simply reply to this email or reach out to our <a href=\"" + supportUrl + "\">support team</a> for help.</p>\n" +
                "                    <p>Sincerely,\n" +
                "                      <br>The MicroGrowth team</p>\n" +
                "<table class=\"email-wrapper\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\">\n" +
                "      <table class=\"email-content\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "        <tr>\n" +
                "          <td class=\"email-masthead\">\n" +
                "            <a href=\"https://example.com\" class=\"f-fallback email-masthead_name\">\n" +
                "              MicroGrowth\n" +
                "            </a>\n" +
                "          </td>\n" +
                "        </tr>\n" +
                "        <!-- Email Body -->\n" +
                "        <tr>\n" +
                "          <td class=\"email-body\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "            <table class=\"email-body_inner\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "              <!-- Body content -->\n" +
                "              <tr>\n" +
                "                <td class=\"content-cell\">\n" +
                "                  <div class=\"f-fallback\">\n" +
                "                    <h1>Hi,</h1>\n" +
                "                    <p>We're excited to have you onboard! To get started, please click on the button below to download your PDF:</p>\n" +
                "                    <!-- Action -->\n" +
                "                    <table class=\"body-action\" align=\"center\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "                      <tr>\n" +
                "                        <td align=\"center\">\n" +
                "                          <!-- Border based button\n" +
                "           https://litmus.com/blog/a-guide-to-bulletproof-buttons-in-email-design -->\n" +
                "                          <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" role=\"presentation\">\n" +
                "                            <tr>\n" +
                "                              <td align=\"center\">\n" +
                "                                <a href=\" class=\"f-fallback button button--blue\" target=\"_blank\">Download as PDF</a>\n" +
                "                              </td>\n" +
                "                            </tr>\n" +
                "                          </table>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "                    <!-- Sub copy -->\n" +
                "                    <table class=\"body-sub\" role=\"presentation\">\n" +
                "                      <tr>\n" +
                "                        <td>\n" +
                "                          <p class=\"f-fallback sub\"><strong>Need a printable copy for your records?</strong> You can <a href=\"{{action_url}}\">download a PDF version</a>.</p>\n" +
                "                          <p class=\"f-fallback sub\">Moved recently? Have a new credit card? You can easily <a href=\"{{billing_url}}\">update your billing information</a>.</p>\n" +
                "                        </td>\n" +
                "                      </tr>\n" +
                "                    </table>\n" +
                "</div>\n" +
                "      <tr>\n" +
                "        <td>\n" +
                "          <table class=\"email-footer\" align=\"center\" width=\"570\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\">\n" +
                "            <tr>\n" +
                "              <td class=\"content-cell\" align=\"center\">\n" +
                "                <p class=\"f-fallback sub align-center\">\n" +
                "                  MicroGrowth, Marketing Team\n" +
                "                  <br>Esprit\n" +
                "                  <br>Pleasure making business with you ! \n" +
                "                </p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          </table>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "</div></div>";




    }
}
