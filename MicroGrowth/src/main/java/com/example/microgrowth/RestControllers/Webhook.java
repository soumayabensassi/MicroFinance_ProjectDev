package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
//import com.example.microgrowth.Model.WebhookRequest;
import com.example.microgrowth.Model.WebhookResponse;
//import com.google.cloud.dialogflow.v2.QueryResult;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/webhook1")

public class Webhook {



    private InsuranceRepository insuranceRepository;





}
