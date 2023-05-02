package com.example.microgrowth.Model;

import com.google.cloud.dialogflow.v2.Intent;
import com.google.cloud.dialogflow.v2.OriginalDetectIntentRequest;
import com.google.cloud.dialogflow.v2.QueryResult;

import java.util.Map;

public class WebhookRequest {
    private String responseId;
    private QueryResult queryResult;
    private OriginalDetectIntentRequest originalDetectIntentRequest;
    private String session;
    private Map<String, Object> queryParams;
    private Map<String, Object> outputContexts;
    private Intent currentIntent;
    private String languageCode;

    public QueryResult getQueryResult() {
        return queryResult;
    }
}
