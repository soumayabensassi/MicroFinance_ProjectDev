package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Message;
//import com.example.microgrowth.DAO.Entities.ProfanityDetector;
import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Classe.MessageService;
//import com.example.microgrowth.Service.Classe.ProfanityFilterService;

import com.example.microgrowth.Service.Classe.UserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.tokenize.SimpleTokenizer;
@RestController
@AllArgsConstructor

public class MessageRestController {

    private UserService userService;
    //private DoccatModel model;


   // public MessageRestController() throws IOException {
        // Charger le modèle pour la détection de gros mots
     //   InputStream modelIn = getClass().getResourceAsStream("C:/Users/HP/Documents/opennlp.bin");
     //   model = new DoccatModel(modelIn);
     //   modelIn.close();
   // }

    private MessageService messageService;
    //@Autowired
    //private ProfanityFilterService profanityFilterService;
    //@Autowired
    //public MessageRestController(ProfanityFilterService profanityFilterService) {
    // this.profanityFilterService = profanityFilterService;
    //}

    //@Autowired
    //private ProfanityFilterService profanityFilterService;

    //@PostMapping("/{senderUsername}/{receiverUsername}")
    //public void sendMessage(@PathVariable int senderUsername, @PathVariable int receiverUsername, @RequestBody String content) {
    //  messageService.sendMessage(senderUsername, receiverUsername, content);
    //}
    @PostMapping("/messagee")
    public ResponseEntity<?> createMessage(@RequestBody Message message) {
        {List<String> motsARechercher = Arrays.asList("mot1", "mot2", "mot3");
            String texte = message.getContent();
            for (String mot : motsARechercher) {
                if (texte.contains(mot)) {
                    message.setSentAt(LocalDateTime.now());
                    message.setContent("*****");
                    messageService.save(message);

                    return ResponseEntity.badRequest().body("message censuré");
                }
            }}
        message.setSentAt(LocalDateTime.now());
        messageService.save(message);
        return ResponseEntity.ok().build();}
   // }

    @GetMapping("/messagees/{sender}/{recipient}")
    public List<Message> getMessages(@PathVariable String sender, @PathVariable String recipient) {
        User senderUser = userService.findByUsername(sender);
        User recipientUser = userService.findByUsername(recipient);
        List<Message> messages = messageService.findBySenderAndRecipient(senderUser, recipientUser);
        messages.addAll(messageService.findBySenderAndRecipient(recipientUser, senderUser));
        Collections.sort(messages, Comparator.comparing(Message::getSentAt));
        return messages;
    }

    @GetMapping("/messagees/{recipient}")
    public List<Message> getMessagesByRecipient(@PathVariable String recipient) {
        User recipientUser = userService.findByUsername(recipient);
        return messageService.findByRecipient(recipientUser);
    }
    //private ProfanityDetector profanityDetector;

   // @Autowired
    //public MessageRestController() throws IOException {
       // profanityDetector = new ProfanityDetector();}
    @PostMapping("/messageezz")
    public Message createMessage1(@RequestBody Message message) {
        //if (profanityDetector.containsProfanity(message.getContent())) {
          //  message.setContent("Message censuré");
        //}
        message.setSentAt(LocalDateTime.now());
       return messageService.save(message);
        //return ResponseEntity.ok().build();}
    }

// code json sur postman
//{
       // "content": "Hello !",
          //      "sender": {
          //  "idUser": 4,
        //            "firstName": "ddd"
        //},
        //"recipient": {
         //   "idUser": 5,
       //             "firstName": "mimi"
       // }
    //}

    @MessageMapping ("/message")

    @SendTo("/topic/return-to")
    public Message getContent(@RequestBody Message message){
     //  try
     //  {
         //  Thread.sleep(2000);

      // }catch(InterruptedException e ){
        //   e.printStackTrace();
       //}

        return message;
    }
}
