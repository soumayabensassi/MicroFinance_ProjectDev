package com.example.microgrowth;

import com.example.microgrowth.DAO.Entities.User;
import com.example.microgrowth.Service.Classe.MicroGrowthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MicroGrowthApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroGrowthApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
