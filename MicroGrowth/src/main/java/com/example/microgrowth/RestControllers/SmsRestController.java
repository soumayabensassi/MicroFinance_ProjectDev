package com.example.microgrowth.RestControllers;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@RestController
public class SmsRestController {

    @GetMapping(value = "/sendSMS")
    public ResponseEntity<String> sendSMS() {

        Twilio.init("ACbefcebcd919893a1dd413e7a6c06d7fd", "5c8080a85e3b4eb905967b4d5b0a2e59");

        Message.creator(new PhoneNumber("+21653367057"),
                new PhoneNumber("+15075843922"), "Hello from Twilio 📞").create();

        return new ResponseEntity<String>("Message sent successfully", HttpStatus.OK);
    }
}