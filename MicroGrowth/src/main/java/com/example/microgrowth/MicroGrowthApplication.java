package com.example.microgrowth;

import com.example.microgrowth.config.SSLVerificationDisabler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableScheduling
public class MicroGrowthApplication {

    public static void main(String[] args) throws Exception {
        SSLVerificationDisabler.disableSSLVerification();
        SpringApplication.run(MicroGrowthApplication.class, args);
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
