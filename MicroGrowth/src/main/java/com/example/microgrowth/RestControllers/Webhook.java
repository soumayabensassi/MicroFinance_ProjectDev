package com.example.microgrowth.RestControllers;

import com.example.microgrowth.DAO.Entities.Inssurance;
import com.example.microgrowth.DAO.Repositories.InsuranceRepository;
import com.example.microgrowth.Model.WebhookRequest;
import com.example.microgrowth.Model.WebhookResponse;
import com.google.cloud.dialogflow.v2.QueryResult;
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



    @PostMapping("/webhook")
    public WebhookResponse handleWebhook(@RequestBody WebhookRequest request) {


        QueryResult queryResult = request.getQueryResult();
        String intent = queryResult.getIntent().getDisplayName();

        String redirectUrl = "";



        if (intent.equals("access.insurance.interface")) {
            redirectUrl = "google.com";
        } else if (intent.equals("Credit")) {
            redirectUrl = "/api/Credits";
        } else if (intent.equals(("insurance.request"))) {
            String Samount = queryResult.getQueryText();
            float amount = Float.parseFloat(Samount);

            // Store insurance response in the database
            Inssurance inssurance = new Inssurance();
            inssurance.setAmount(amount);
            insuranceRepository.save(inssurance);
        } else if ("balance.check".equals(request.getQueryResult().getIntent().getDisplayName())) {
            // TODO: Retrieve the balance from your entity and return it as a response
            String balance = "1000"; // Example response
            return new WebhookResponse("Your balance is $" + balance);
            
        }

        WebhookResponse webhookResponse = new WebhookResponse();
        webhookResponse.setFulfillmentText("Redirecting you to " + redirectUrl);
        webhookResponse.setSource("example.com");

        Map<String, Object> payload = new HashMap<>();
        String finalRedirectUrl = redirectUrl;
        payload.put("web", new HashMap<String, String>() {{
            put("url", finalRedirectUrl);
        }});
        webhookResponse.setPayload(payload);

        return webhookResponse;
    }

}
