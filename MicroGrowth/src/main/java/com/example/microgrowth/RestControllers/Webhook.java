package com.example.microgrowth.RestControllers;

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
@RequestMapping("/webhook")

public class Webhook {






    @PostMapping("/webhook")
    public WebhookResponse handleWebhook(@RequestBody WebhookRequest request) {
        QueryResult queryResult = request.getQueryResult();
        String userResponse = queryResult.getQueryText();
        String redirectUrl = "";

        if (userResponse.equals("insurance")) {
            redirectUrl = "/api/insurances";
        } else if (userResponse.equals("Credit")) {
            redirectUrl = "/api/Credits";
        } else if (userResponse.equals(("Investment"))) {
            redirectUrl = "/api/Investments";
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
