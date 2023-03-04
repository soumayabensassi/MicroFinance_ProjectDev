package com.example.microgrowth.RestControllers;

import com.example.microgrowth.Service.Classe.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class SendEmailRestController {
private EmailService emailService;

//    @GetMapping("/forgetpassword/{email}")
//    public String forgetpassword(@PathVariable("email") String email) {
//        return emailService.forgetpassword(email);
//    }

    @PostMapping("/MicroGrowth/user/password/{email}")
    public String test(@PathVariable String email) {

//        return "done";
        return emailService.forgetpassword(email);

    }
    @GetMapping("/MicroGrowth/user/email/reset/{token}/{email}/{password}")
    public String reset(@PathVariable("token") String token,@PathVariable("email") String email,@PathVariable("password") String password) {
        return emailService.resetPassword(token,email,password);
    }
}
