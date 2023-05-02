package com.example.microgrowth.RestControllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class SmsRestController {

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

//        Twilio.init("ACbefcebcd919893a1dd413e7a6c06d7fd", "dfa1b6f93845e9377b10517a0fc1d792");

      return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}