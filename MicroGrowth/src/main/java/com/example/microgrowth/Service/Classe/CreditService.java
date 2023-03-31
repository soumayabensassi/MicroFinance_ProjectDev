package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.*;
import com.example.microgrowth.DAO.Repositories.*;
import com.example.microgrowth.Service.Interfaces.*;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.internet.MimeMultipart;
@AllArgsConstructor
@Service
public class CreditService implements ICredit {
    @Autowired
    CreditRepository creditRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    IInvestment iInvestment;
    @Autowired
    IMicroGrowth iMicroGrowth;
    @Autowired
    IUser iUser;


    @Override
    public Credit add_credit_user(Credit c) {
        Calendar calendar = Calendar.getInstance();
        //calendar.setTime(c.getObtainingDate());
// Ajouter 30 jours à la date
        calendar.add(Calendar.DATE, 30);
       // Date dateApresAjout = calendar.getTime();
        c.setPack(false);
      //  c.setDateEcheance(dateApresAjout);
        c.setState(1); //1:en cours  0:refus  2:accordé
        Date date_now = new Date();
        c.setDemandDate(date_now);
        System.out.println("aaaa");
        c.setUsers(iUser.getUserByEmail(iMicroGrowth.getCurrentUserName()));
        System.out.println(c.getUsers().getFirstName());
        c.setIntrestRaiting(calculateInterestRate(c));
        System.out.println(c.getIntrestRaiting());
        //iMicroGrowth.getUser(iMicroGrowth.getCurrentUserName());
        // c.setUsers(iMicroGrowth.getUser("aziz2000cherif1@gmail.com"));
        //float ca=calcul_taux(c.getAmount_credit(),c.getDuree() );
        ///c.setIntrestRaiting(ca);

        return creditRepository.save(c);
    }

    @Override
    public Credit add_credit_admin(Credit c) {
        c.setPack(true);

        return creditRepository.save(c);
    }

    @Override
    public Credit edit(Credit c) {
        return creditRepository.save(c);
    }

    @Override
    public List<Credit> selectAll() {
        return creditRepository.findAll();
    }

    @Override
    public Credit SelectById(int id_credit) {
        return creditRepository.findById(id_credit).get();
    }

    @Override
    public void deleteById(int id_credit) {
        creditRepository.deleteById(id_credit);
    }

    @Override
    public List<Credit> SelectByEmail(String email) {
        return creditRepository.findByUsersEmail(email);
    }


    @Override
    public int scoreCredit(int id) {
        int score = 0;
        Credit credit = creditRepository.findById(id).orElse(null);
        User user = userRepository.findById(credit.getUsers().getIdUser()).orElse(null);
        float revenuMensuel = user.getSalaire();
        int ancienneteEmploi = user.getAncienneteEmploi();
        float montantPret = credit.getAmount_credit();
        int dureePret = credit.getDuree();
        float ratioEndettement = credit.getIntrestRaiting();
        Date date_now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String yearString = yearFormat.format(date_now);
        int year1 = Integer.parseInt(yearString);

        SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy");
        String yearString2 = yearFormat.format(user.getDateNaissance());
        int year2 = Integer.parseInt(yearString2);
        int age = year1 - year2;
        boolean garanties = credit.isGaranties();
        boolean historiqueCredit = user.isHistoriqueCredit();


        //Critère 1: Revenu mensuel
        if (revenuMensuel >= 3000 && revenuMensuel <= 5000) {
            score += 20;
        } else if (revenuMensuel > 5000) {
            score += 40;
        }

        //Critère 2: Ancienneté de l'emploi
        if (ancienneteEmploi >= 2 && ancienneteEmploi <= 5) {
            score += 10;
        } else if (ancienneteEmploi > 5) {
            score += 20;
        }

        //Critère 3: Montant et durée du prêt
        if (montantPret >= 5000 && montantPret <= 10000 && dureePret <= 24) {
            score += 10;
        } else if (montantPret > 10000 && dureePret > 24) {
            score += 20;
        }

        //Critère 4: Ratio d'endettement
        if (ratioEndettement < 0.3) {
            score += 20;
        } else if (ratioEndettement >= 0.3 && ratioEndettement <= 0.4) {
            score += 10;
        }


        //Critère 6: Age
        if (age >= 25 && age <= 40) {
            score += 10;
        } else if (age > 40 && age <= 50) {
            score += 5;
        }

        //Critère 7: Garanties
        if (garanties) {
            score += 10;
        }

        //Critère 8: Historique de crédit
        if (historiqueCredit) {
            score += 10;
        }

        return score;
    }

    @Override
    public float calcul_taux(float montant, int duree) {
        return 0;
    }

    @Override
    public void calcul_tableau_credit(Credit c) {
        float amt, interest, monthly_payment;
        float[][] matrice = new float[c.getDuree() * 12][4];

        matrice[0][0] = c.getAmount_credit();
        matrice[0][1] = (matrice[0][0] * c.getIntrestRaiting()) / 12;
        matrice[0][2] = matrice[0][0] / (c.getDuree() * 12);
        amt = matrice[0][2];

        matrice[0][3] = amt + matrice[0][1];


        for (int j = 1; j < c.getDuree() * 12; j++) {
            matrice[j][0] = matrice[j - 1][0] - amt;//montant
            matrice[j][1] = (matrice[j][0] * c.getIntrestRaiting()) / 12;//interet
            matrice[j][2] = matrice[0][0] / (c.getDuree() * 12);//amt
            matrice[j][3] = amt + matrice[j][1];//mensualite

        }
        for (int j = 0; j < matrice.length; j++) {
            for (int i = 0; i < 4; i++) {
                System.out.print(matrice[j][i] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public double calcul_Rentabilite_parCredit(Credit c) {

        double Taux_Actualisation = 0.1;
        double Taux_Actualisationparmois = Math.pow(1 + Taux_Actualisation, 0.08333333) - 1;
        double tauxeparmois = Math.pow(1 + c.getIntrestRaiting(), 0.08333333) - 1;
        double S = Math.pow(1 + tauxeparmois, c.getDuree() * 12);
        double tauxdActualisationConverti = Math.pow(1 + Taux_Actualisationparmois, c.getDuree() * 12);
        double Sa = (S - 1) / tauxeparmois;
        double cap = 1 / tauxdActualisationConverti;


        double produit = c.getMonthlyAmount() * Sa * cap;

        return produit - c.getAmount_credit();

    }

    @Override
    public double calcul_Rentabilite_parCreditNonActialise(Credit c) {

        double tauxeparmois = Math.pow(1 + c.getIntrestRaiting(), 0.08333333) - 1;
        double S = Math.pow(1 + tauxeparmois, c.getDuree() * 12);
        double Sa = (S - 1) / tauxeparmois;
        double produit = c.getMonthlyAmount() * Sa;

        return produit - c.getAmount_credit();
    }

    @Override
    public double calcul_Net_Interest_Marge() {
        List<Credit> creditList = this.selectAll();
        double interet_percus_prets = 0;

        for (Credit c : creditList) {
            interet_percus_prets += (c.getMonthlyAmount() * c.getDuree() * 12) - c.getAmount_credit();
        }
        //taux d'ineret des depots en tunisie est 1%
        double interet_payes_depots = 0;
        for (Credit c : creditList) {
            interet_payes_depots += (c.getAmount_credit() * Math.pow(1.01, c.getDuree())) - c.getAmount_credit();
        }
        double total_actif_productif_interet = 0;
        for (Credit c : creditList) {
            total_actif_productif_interet += c.getAmount_credit() * c.getIntrestRaiting();
        }
        return (interet_percus_prets - interet_payes_depots) / total_actif_productif_interet;
    }

    @Override
    public double TauxRemboursement() {
        double taux = 0;
        List<Credit> creditList = this.selectAll();
        double totaldesprets = 0;

        for (Credit c : creditList) {
            totaldesprets += c.getAmount_credit() * c.getDuree() * 12;
        }
        List<Credit> creditListRembouse = creditRepository.creditRembouse(true);

        double totaldespretsrembourses = 0;

        for (Credit c : creditListRembouse) {
            totaldespretsrembourses += c.getAmount_credit() * c.getDuree() * 12;
        }
        System.out.println("TauxDeRemboursement:" + totaldespretsrembourses);
        System.out.println("totaldesprets:" + totaldesprets);
        taux = totaldespretsrembourses / totaldesprets;
        return taux;
    }

    @Override
    public double TauxDeDefaut() {
        double taux = 0;
        List<Credit> creditList = this.selectAll();
        double totaldesprets = 0;

        for (Credit c : creditList) {
            totaldesprets += c.getAmount_credit() * c.getDuree() * 12;
        }
        List<Credit> creditListRembouse = creditRepository.creditRembouse(false);

        double totaldespretsNOMrembourses = 0;

        for (Credit c : creditListRembouse) {
            totaldespretsNOMrembourses += c.getAmount_credit() * c.getDuree() * 12;
        }
        System.out.println("TauxDeRemboursement:" + totaldespretsNOMrembourses);
        System.out.println("totaldesprets:" + totaldesprets);
        taux = totaldespretsNOMrembourses / totaldesprets;
        return taux;
    }

    @Override
    public double CalculActifCredit() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        int year = calendar.get(Calendar.YEAR);
        double actif = 0;
        List<Credit> creditdeAnnee = creditRepository.creditParAnnee(year);

        for (Credit c : creditdeAnnee) {
            double test = 0;
            System.out.println("idUser=" + c.getUsers().getIdUser());
            System.out.println("annee=" + year);
            List<Integer> transaction = creditRepository.listTransactiondelannee(c.getUsers().getIdUser(), year);
            System.out.println(transaction);
            if (!transaction.isEmpty()) {
                test = creditRepository.SommeDepot(c.getUsers().getIdUser(), year);
            }

            actif += (c.getMonthlyAmount() * c.getDuree() * 12) - test;

        }
        System.out.println("actif:"+actif);
        return actif;
    }

    @Override
    public double CalculActifRéserve() {

        BankAccount bankAccount = bankAccountRepository.getBankAccountByRib("1");
        return bankAccount.getAmount() * 0.1;
    }

    @Override
    public double CalculResultatNET() {
        double resultatCredit = 0;
        double resultatInvestissement = 0;
        double resultatNet = 0;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Annee=" + year);
        List<Credit> creditdeAnnee = creditRepository.creditParAnnee(year);
        for (Credit c : creditdeAnnee) {
            resultatCredit += this.calcul_Rentabilite_parCreditNonActialise(c);
        }
        List<Investment> investmentList = iInvestment.getInvestmentByAnnee();
        for (Investment i : investmentList) {
            resultatInvestissement += iInvestment.Revenu_INVISTISSEMENT(i);
        }
        resultatNet = (resultatCredit + resultatInvestissement) - ((resultatCredit + resultatInvestissement) * 0.25);
        return resultatNet;
    }

    @Override
    public void ajouter_date_30(Credit c) {

        Date date_now = new Date();
        if (date_now.compareTo(c.getDateEcheance()) > 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(c.getObtainingDate());
// Ajouter 30 jours à la date
            calendar.add(Calendar.DATE, 30);
            Date date_incremented = calendar.getTime();
            c.setDateEcheance(date_incremented);
            this.edit(c);
            System.out.println("c est bon ");

        }

    }


    @Autowired
    PenaliteRepository penaliteRepository;
    @Autowired
    IPenalite iPenalite;

    @Override
    public void Accorde_penalite() {
        boolean penalite_existe = false;
        List<Credit> listCreditPaye = creditRepository.selectCreditRembourseeParMois();

        List<Penalite> listPenalite = penaliteRepository.findAll();
        //listCreditNonPaye.remove(listCreditPaye);
        for (Credit i : listCreditPaye) {
            System.out.println(i.getIdCredit());
            this.ajouter_date_30(i);
        }
        List<Credit> listCreditNonPaye = creditRepository.findAll();
        listCreditNonPaye.removeIf(element -> listCreditPaye.contains(element));
        for (Credit i : listCreditNonPaye) {


            //i.setPenalites(i.getPenalites()+1);
            //i.setMontant_penalites(i.getMonthlyAmount()+i.getMonthlyAmount()*i.getIntrestRaiting());
            for (Penalite pe : listPenalite) {
                if (pe.getCredits() == i) {
                    penalite_existe = true;
                }
            }


            if (penalite_existe == false) {
                Penalite p = new Penalite();
                System.out.println(i.getIdCredit());
                p.setDatePenalite(i.getDateEcheance());

                p.setMontant_penalite(i.getMonthlyAmount() +  (i.getMonthlyAmount() * i.getIntrestRaiting() * 2));
                i.setMontant_penalites(p.getMontant_penalite() +i.getMontant_penalites());
                p.setCredits(i);
                p.setPaye(false);
                i.setPenalites(i.getPenalites()+1);
                System.out.println("*********");

                iPenalite.add(p);
                System.out.println("*********");
             }

        }

    }

    TransactionRepository transactionRepository;

    @Override
    public void annuler_penalite() {
        List<Credit> listcredit = creditRepository.findAll();
        List<Transaction> listtrasacion = transactionRepository.findAll();
        for (Credit c : listcredit) {


        }
    }

    @Override
    public void AffecterCreditAuUser(Credit credit) {
        Date date_now = new Date();
        credit.setObtainingDate(date_now);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(credit.getObtainingDate());
// Ajouter 30 jours à la date
        calendar.add(Calendar.DATE, 30);
        Date dateApresAjout = calendar.getTime();
        credit.setDateEcheance(dateApresAjout);
        credit.setState(2); //1:en cours  0:refus  2:accordé
        System.out.println(credit.getDateEcheance());
creditRepository.save(credit);
    }

    @Override
    public void RefuserCreditAuUser(Credit credit) {
        credit.setState(0);
        creditRepository.save(credit);//1:en cours  0:refus  2:accordé
    }

    @Override
    public float calculateInterestRate(Credit c) {

        float baseInterestRate = 0.2f; // taux d'intérêt de base pour la microfinance
        float interestRate = baseInterestRate;
        User user = userRepository.findById(c.getUsers().getIdUser()).orElse(null);
        Date date_now = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String yearString = yearFormat.format(date_now);
        int year1 = Integer.parseInt(yearString);

        SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy");
        String yearString2 = yearFormat.format(user.getDateNaissance());
        int year2 = Integer.parseInt(yearString2);
        // Vérification de l'âge du client
        int age = year1 - year2;

        double ageFactor = 1.0;
        if (age < 25) {
            ageFactor = 1.2; // augmentation de 20% du taux d'intérêt pour les jeunes emprunteurs
        } else if (age >= 65) {
            ageFactor = 0.9; // diminution de 10% du taux d'intérêt pour les emprunteurs âgés
        }
        interestRate *= ageFactor;

        // Vérification du niveau de revenu du client
        double income = user.getSalaire();
        double incomeFactor = 1.0;
        if (income < 500) {
            incomeFactor = 1.3; // augmentation de 30% du taux d'intérêt pour les emprunteurs à faible revenu
        } else if (income >= 1000) {
            incomeFactor = 0.8; // diminution de 20% du taux d'intérêt pour les emprunteurs à revenu élevé
        }
        interestRate *= incomeFactor;

        // Vérification de l'historique de crédit du client

        double creditHistoryFactor = 1.0;
        if (user.isHistoriqueCredit()==false) {
            creditHistoryFactor = 1.2; // augmentation de 50% du taux d'intérêt pour les emprunteurs ayant déjà fait défaut
        }
            interestRate *= creditHistoryFactor;

            // Vérification de la durée du prêt
            int loanTerm =c.getDuree();
            double loanTermFactor = 1.0;
            if (loanTerm < 6) {
                loanTermFactor = 1.3; // augmentation de 30% du taux d'intérêt pour les prêts de courte durée
            } else if (loanTerm > 12) {
                loanTermFactor = 0.9; // diminution de 10% du taux d'intérêt pour les prêts de longue durée
            }
            interestRate *= loanTermFactor;

            // Vérification du montant du prêt demandé par le client
            double loanAmount = c.getAmount_credit();
            double loanAmountFactor = 1.0;
            if (loanAmount > 2000) {
                loanAmountFactor = 1.2; // augmentation de 20% du taux d'intérêt pour les montants de prêt élevés
            }
            interestRate *= loanAmountFactor;

            return interestRate;

        }
        public void SimulateurCredit(float MontantCredit , int nbmois){
            double taux = 0.23  ;
//            float salaire=iUser.getUserByEmail(iMicroGrowth.getCurrentUserName()).getSalaire();


            double tauxMensuel = taux / 12;
            double mensualite = (MontantCredit * tauxMensuel) / (1 - Math.pow(1 + tauxMensuel, -nbmois));

            System.out.println("Taux d'intérêt annuel : " + taux);
            System.out.println("Mensualité : " + mensualite);
            System.out.println("Coût total sans assurance : " + (mensualite * nbmois - MontantCredit));


        }
public double MaxCredit(int nbmois){
       User user=iUser.getUserByEmail(iMicroGrowth.getCurrentUserName());
        double taux=0.3;
        double tauxMensuel = taux / 12;
        double MaxCredit=((user.getSalaire()*0.43)*(1 - Math.pow(1 + tauxMensuel, -nbmois))) / tauxMensuel;
        return MaxCredit;
}
    public File genererCreditPDF(int nbmois) throws IOException, DocumentException {
        User user = iUser.getUserByEmail(iMicroGrowth.getCurrentUserName());
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("credit.pdf"));

        // Ajouter le header
        HeaderFooter header = new HeaderFooter(new Phrase("MicroGrowth"), false);
        header.setAlignment(Element.ALIGN_CENTER);
        document.setHeader(header);

        // Ajouter le footer
        Font FooterFont = new Font(Font.HELVETICA, 7, Font.NORMAL);

        Paragraph footerText = new Paragraph("Le résultat de cette simulation est non contractuel et revêt un caractère strictement informatif.\n" +
                "Elle ne prend pas en compte le coût des assurances nécessaires au crédit, ni la TVA réglementaire. Il ne s’agit en aucun cas\n" +
                "d’un engagement de la part de MicroGrowth qui se réserve le droit de modifier à tout moment l’une ou l’autre des données et des\n" +
                "conditions de financement de ses offres de crédits.",FooterFont);

        footerText.setSpacingBefore(10f); // ajoute un espace de 10 points avant le texte du footer

        HeaderFooter footer = new HeaderFooter(footerText, new Phrase(" - Page ",FooterFont));
        footer.setAlignment(Element.ALIGN_LEFT);
        document.setFooter(footer);


        // Ouvrir le document
        document.open();

        // Ajouter le logo
        Image logo = Image.getInstance("logo_MicroGrowth.png");
        logo.scaleAbsolute(100f, 100f);
        logo.setAlignment(Element.ALIGN_CENTER);
        document.add(logo);

        // Ajouter les informations du contrat
        double max = MaxCredit(nbmois);
        Font boldFont = new Font(Font.HELVETICA, 16, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
        Paragraph montantMax = new Paragraph("Montant maximum : " + max + " dinars", boldFont);
        montantMax.setSpacingAfter(20f);
        document.add(montantMax);

        Paragraph mensualite = new Paragraph("Mensualité : " + user.getSalaire()*0.43 + " dinars", normalFont);
        mensualite.setSpacingAfter(20f);
        document.add(mensualite);

        Paragraph duree = new Paragraph("Durée : " + nbmois + " mois", normalFont);
        duree.setSpacingAfter(20f);
        document.add(duree);

        Paragraph taux = new Paragraph("Taux d'intérêt : 0.3%", normalFont);
        taux.setSpacingAfter(20f);
        document.add(taux);

        // Fermer le document
        document.close();
        return null;
    }
    public void envoyerCreditParEmail() throws javax.mail.MessagingException {
        User user=iUser.getUserByEmail(iMicroGrowth.getCurrentUserName());
        String smtpHost = "smtp.gmail.com";
        String smtpPort = "587";
        String smtpUsername = "microgrowth.pi@gmail.com";
        String smtpPassword = "viqybbgdubrswjnz";
        String sender = "microgrowth.pi@gmail.com";
        String subject = "Montant maximum du Crédit";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(smtpUsername, smtpPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
        message.setSubject(subject);

        // Créer le contenu du message

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Bonjour Mr"+ user.getLasttName()+" "+ user.getFirstName() + ",\n\nVeuillez trouver ci-joint le montant maximal que vous pouvez emprunter \""  + "\".");

        // Créer la pièce jointe PDF
        MimeBodyPart pdfAttachment = new MimeBodyPart();
        DataSource source = new FileDataSource("credit.pdf");
        pdfAttachment.setDataHandler(new DataHandler(source));
        pdfAttachment.setFileName("credit.pdf");

        // Ajouter la pièce jointe PDF au message
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(pdfAttachment);
        message.setContent(multipart);
        // Envoyer le message
        Transport.send(message);
    }

}


