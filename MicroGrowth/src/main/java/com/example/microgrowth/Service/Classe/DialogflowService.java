package com.example.microgrowth.Service.Classe;

import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.Model.WebhookRequest;
import com.example.microgrowth.Model.WebhookResponse;
import com.google.cloud.dialogflow.v2.QueryResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor

public class DialogflowService {

    private final InsuranceRepository insuranceRepository;

    public WebhookResponse handleWebhook(WebhookRequest request) {
        QueryResult queryResult = request.getQueryResult();
        String Samount = queryResult.getQueryText();
        float amount = Float.parseFloat(Samount);
        String redirectUrl = "";



        // Store insurance response in the database
        Inssurance inssurance = new Inssurance();
        inssurance.setAmount(amount);
        insuranceRepository.save(inssurance);

        // Build and return webhook response
        WebhookResponse webhookResponse = new WebhookResponse();
        webhookResponse.setFulfillmentText("Redirecting you to " + redirectUrl);
        webhookResponse.setSource("example.com");

        Map<String, Object> payload = new HashMap<>();
        payload.put("web", new HashMap<String, String>() {{
            put("url", redirectUrl);
        }});
        webhookResponse.setPayload(payload);

        return webhookResponse;
    }

}

