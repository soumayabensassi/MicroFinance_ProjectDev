package com.example.microgrowth.Model;

import lombok.AllArgsConstructor;

import java.util.Map;
@AllArgsConstructor
public class WebhookResponse {

    private String fulfillmentText;
    private String source;
    private Map<String, Object> payload;

    public WebhookResponse(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText ;
    }
    public WebhookResponse (){

    }

    public String getFulfillmentText() {
        return fulfillmentText;
    }

    public void setFulfillmentText(String fulfillmentText) {
        this.fulfillmentText = fulfillmentText;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, Object> getPayload() {
        return payload;
    }

    public void setPayload(Map<String, Object> payload) {
        this.payload = payload;
    }
}
